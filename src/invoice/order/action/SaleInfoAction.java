package invoice.order.action;

import java.util.List;

import invoice.order.business.ebi.SaleInfoEbi;
import invoice.order.vo.SaleInfoModel;
import invoice.order.vo.SaleInfoQueryModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("saleInfoAction")
@Scope("prototype")
public class SaleInfoAction extends BaseAction{
	public SaleInfoModel sm = new SaleInfoModel();
	public SaleInfoQueryModel sqm = new SaleInfoQueryModel();

	@Resource
	private SaleInfoEbi saleInfoEbi;

	//列表
	public String list(){
		setTotalCount(saleInfoEbi.getCount(sqm));
		List<SaleInfoModel> saleInfoList = saleInfoEbi.getAll(sqm,pageNum,pageCount);
		put("saleInfoList", saleInfoList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = saleInfoEbi.getByUuid(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			saleInfoEbi.save(sm);
		}else{
			saleInfoEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		saleInfoEbi.delete(sm);
		return TO_LIST;
	}

}
