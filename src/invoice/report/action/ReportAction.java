package invoice.report.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Supplier;

import invoice.orderdetail.business.ebi.OrderDetailEbi;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.report.business.ebi.ReportEbi;
import invoice.report.vo.ReportQueryModel;
import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.vo.SupplierModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller("reportAction")
@Scope("prototype")
public class ReportAction extends BaseAction{
	public ReportQueryModel rqm = new ReportQueryModel();

	@Resource
	private ReportEbi reportEbi;
	@Resource
	private SupplierEbi supplierEbi;

	@Resource
	private OrderDetailEbi orderDetailEbi;

	public String buyList() {
		List<Object[]> buyList = reportEbi.getReport(rqm);
		put("buyList",buyList);
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		return "buyList";
	}

	public void pieBill() throws Exception{
		HttpServletResponse response  = getResponse();
		OutputStream os = response.getOutputStream();
		//将jfreechart生成的图片信息写入到os中就行了
		reportEbi.writeJFreeChartToOs(os,rqm);
	}

	private InputStream downloadExcelStream;
	public InputStream getDownloadExcelStream() {
		return downloadExcelStream;
	}
	private String xlsName;
	public String getXlsName() throws UnsupportedEncodingException {
		//字符级要进行过滤
		//xlsName->byte[]->string
		return new String(xlsName.getBytes("utf-8"),"iso8859-1");
	}
	//下载excel报表
	public String download() throws Exception{
		//将要下载的内容写入downloadExcelStream中
		xlsName = "buy_report.xls";
		downloadExcelStream = reportEbi.getWriteExcelStream(rqm);
		return "download";
	}



	//-----------------------------------
	//-----------------------------------
	//--------------ajax-----------------
	//-----------------------------------
	//-----------------------------------

	public Long gmUuid;
	private List<OrderDetailModel> odms;
	public List<OrderDetailModel> getOdms() {
		return odms;
	}

	public String ajaxGetGmDetail() {

		odms = orderDetailEbi.getGmList(rqm,gmUuid);

		return "ajaxGetGmDetail";
	}

}
