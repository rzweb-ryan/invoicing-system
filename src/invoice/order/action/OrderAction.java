package invoice.order.action;

import java.util.List;

import auth.emp.business.ebi.EmpEbi;
import auth.emp.vo.EmpModel;
import invoice.goods.business.ebi.GoodsEbi;
import invoice.goods.vo.GoodsModel;
import invoice.goodsType.business.ebi.GoodsTypeEbi;
import invoice.goodsType.vo.GoodsTypeModel;
import invoice.order.business.ebi.OrderEbi;
import invoice.order.business.ebi.SaleInfoEbi;
import invoice.order.vo.OrderModel;
import invoice.order.vo.OrderQueryModel;
import invoice.order.vo.SaleInfoModel;
import invoice.order.vo.SaleInfoQueryModel;
import invoice.orderdetail.business.ebi.OrderDetailEbi;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.storage.business.ebi.StorageEbi;
import invoice.storage.vo.StorageModel;
import invoice.storageDetail.business.ebi.StorageDetailEbi;
import invoice.storageDetail.vo.StorageDetailModel;
import invoice.storageDetail.vo.StorageDetailQueryModel;
import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.vo.SupplierModel;
import org.springframework.core.annotation.Order;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends BaseAction{
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	@Resource
	private OrderEbi orderEbi;
	@Resource
	private SupplierEbi supplierEbi;
	@Resource
	private GoodsTypeEbi goodsTypeEbi;
	@Resource
	private GoodsEbi goodsEbi;
	@Resource
	private OrderDetailEbi orderDetailEbi;
	@Resource
	private EmpEbi empEbi;
	@Resource
	private StorageEbi storageEbi;
	@Resource
	private StorageDetailEbi storageDetailEbi;
	@Resource
	private SaleInfoEbi saleInfoEbi;

	public Long[] storageUuids;
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;

	public SaleInfoModel info = new SaleInfoModel();
	//------------sale--------------------

	public String sale() {
		orderEbi.sale(storageUuids,goodsUuids,nums,prices,getLogin(),info);
		return "sale";
	}



	public String saleList() {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_SALE);
		setTotalCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "saleList";
	}

	public String saleInput(){
		if(om.getUuid()!=null){
			om = orderEbi.getByUuid(om.getUuid());
		}
		List<StorageModel> storageList = storageEbi.getAll();
		List<Object[]> sdmList = storageDetailEbi.getGoodsInStore(storageList.get(0));
		put("storageList",storageList);
		put("sdmList",sdmList);
		return "saleInput";
	}

	public String saleDetail() {
		info = saleInfoEbi.getInfo(om);
		om = orderEbi.getByUuid(om.getUuid());
		List<OrderDetailModel> odms = orderDetailEbi.getByOrderUuid(om.getUuid());
		put("info",info);
		put("odms",odms);
		return "saleDetail";
	}





	//--------------buy-------------------
	public String buy() {
		orderEbi.buy(om,getLogin(),goodsUuids,nums,prices);
		return "buy";
	}
	public String buyDetail() {
		om = orderEbi.getByUuid(om.getUuid());
		List<OrderDetailModel> odms = orderDetailEbi.getByOrderUuid(om.getUuid());
		put("odms",odms);
		return "buyDetail";
	}


	//列表
	public String buyList(){
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		setTotalCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "buyList";
	}

	//到添加
	public String buyInput(){
		if(om.getUuid()!=null){
			om = orderEbi.getByUuid(om.getUuid());
		}
		List<SupplierModel> supplierList = supplierEbi.getAllUnion2();
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAllBySupplier(supplierList.get(0).getUuid());
		List<GoodsModel> goodsList = goodsEbi.getAllByGtm(goodsTypeList.get(0).getUuid());
		put("supplierList",supplierList);
		put("goodsTypeList",goodsTypeList);
		put("goodsList",goodsList);
		return "buyInput";
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}

	//--------------------------------------
	//--------------------------------------
	//--------------Approve-----------------
	//--------------------------------------
	//--------------------------------------

	public String approveList() {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		setTotalCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return "approveList";
	}

	public String toCheck() {
		om = orderEbi.getByUuid(om.getUuid());
		return "toCheck";
	}

	public String approve() {
		orderEbi.approve(om,getLogin());
		return "toApproveList";
	}
	public String disApprove() {
		orderEbi.disApprove(om,getLogin());
		return "toApproveList";
	}
	//--------------------------------------
	//--------------------------------------
	//--------------Transport---------------
	//--------------------------------------
	//--------------------------------------

	public String transportList() {
		setTotalCount(orderEbi.getTransportCount(oqm));
		List<OrderModel> orderList  = orderEbi.getTransportList(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return "transportList";
	}

	public String toAssign() {
		List<EmpModel> empList = empEbi.getAllInGroup(getLogin());
		put("empList",empList);
		om = orderEbi.getByUuid(om.getUuid());
		return "toAssign";
	}
	public String toAssignSale() {
		List<EmpModel> empList = empEbi.getAllInGroup(getLogin());
		put("empList",empList);
		om = orderEbi.getByUuid(om.getUuid());
		info = saleInfoEbi.getInfo(om);
		put("info",info);
		return "toAssignSale";
	}

	public String assignTask() {
		orderEbi.assignTask(om);
		return "toTransportList";
	}

	//show task list of completer
	public String taskList() {
		List<SupplierModel> smList = supplierEbi.getAllUnion2();
		put("smList",smList);
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		setTotalCount(orderEbi.getCountTasks(oqm,getLogin()));
		List<OrderModel> orderList = orderEbi.getTasks(oqm,getLogin(),pageNum,pageCount);
		put("orderList",orderList);
		return "taskList";
	}

	public String taskListSale() {
		SaleInfoQueryModel sqm = new SaleInfoQueryModel();
		om = new OrderModel();
		om.setStatus(OrderModel.ORDER_STATUS_OF_SALE_WAITING);
		setTotalCount(orderEbi.getCountTasks(oqm,getLogin()));
		List<SaleInfoModel> infoList = saleInfoEbi.getTasks(sqm,getLogin(),pageNum,pageCount);
		put("infoList",infoList);
		return "taskListSale";
	}
	public String taskDetail() {
		om = orderEbi.getByUuid(om.getUuid());
		return "taskDetail";
	}

	public String taskDetailSale() {
		info = saleInfoEbi.getByUuid(info.getUuid());
		return "taskDetailSale";
	}

	public String endTask() {
		om = orderEbi.getByUuid(om.getUuid());
		orderEbi.endTransportTask(om);
		if(om.getOrderType().equals(OrderModel.ORDER_ORDERTYPE_OF_BUY)) {
			return "toTaskList";

		}else  {
			return "toTaskListSale";
		}
	}

	//--------------------------------------
	//--------------------------------------
	//--------------store---------------
	//--------------------------------------
	//--------------------------------------
	public String storeOut() {
		oqm.setStatus(OrderModel.ORDER_STATUS_OF_SALE_OUT_STORE);
		setTotalCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return "storeOut";
	}



	public String storeIn() {
		oqm.setStatus(OrderModel.ORDER_STATUS_OF_BUY_IN_STORE);
		setTotalCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return "storeIn";
	}

	public String outDetail() {
		//load all the storages
		om = orderEbi.getByUuid(om.getUuid());
		return "outDetail";
	}


	public String inDetail() {
		//load all the storages
		List<StorageModel> storageList = storageEbi.getAllNotFull();
		put("storageList",storageList);
		om = orderEbi.getByUuid(om.getUuid());
		return "inDetail";
	}

	public StorageDetailQueryModel sdqm = new StorageDetailQueryModel();

	public String inList() {
		setTotalCount(storageDetailEbi.getCount(sdqm));
		List<StorageDetailModel> sdms = storageDetailEbi.getAll(sdqm,pageNum,pageCount);
		put("sdms",sdms);
		return "inList";
	}


	private List<Object[]> sdmList = null;
	public List<Object[]> getSdmList() {
		return sdmList;
	}



	//----------sale-ajax-------------
	public String ajaxGetGmSale (){
		StorageModel sm = storageEbi.getByUuid(storageUuid);
		sdmList = storageDetailEbi.getGoodsInStore(sm);
		return "ajaxGetGmSale";
	}

	private Object[] gmObj =null;
	public Object[] getGmObj() {
		return gmObj;
	}

	public String ajaxGetGoods() {
		List<Object[]> gmList = storageDetailEbi.getGoodsInStoreByGm(gmUuid);
		gmObj  =gmList.get(0);
		return "ajaxGetGoods";
	}



	//---------------ajax-------------
	//---------------ajax-------------
	private List<GoodsTypeModel> goodsTypeList = null;
	public List<GoodsTypeModel> getGtmList() {
		return goodsTypeList;
	}
	private List<GoodsModel> goodsList = null;
	public List<GoodsModel> getGmList() {
		return goodsList;
	}
	private GoodsModel gm;
	public GoodsModel getGm() {
		return gm;
	}

	public Long smUuid;
	public Long gtmUuid;
	public Long gmUuid;
	public String gmUuids;

	public String ajaxGetGtmAndGm() {
		goodsTypeList = goodsTypeEbi.getAllBySmUnion(smUuid);

		GtIter:
		for(int i =goodsTypeList.size()-1;i>=0;i--) {
			goodsList = goodsEbi.getAllByGtm(goodsTypeList.get(i).getUuid());
			for(int j=goodsList.size()-1;j>=0;j--) {
				if(!gmUuids.contains("'"+goodsList.get(j).getUuid()+"'")) {
					continue GtIter;
				}
			}
			goodsTypeList.remove(i);
		}

		goodsList = goodsEbi.getAllByGtm(goodsTypeList.get(0).getUuid());
		for(int i =goodsList.size()-1; i>=0;i--) {
			if(gmUuids.contains("'"+goodsList.get(i).getUuid()+"'")) {
				goodsList.remove(i);
			}
		}
		gm = goodsEbi.getByUuid(goodsList.get(0).getUuid());
		return "ajaxGetGtmAndGm";
	}

	public String ajaxGetGtmBySm() {
		goodsTypeList = goodsTypeEbi.getAllBySmUnion(smUuid);
		goodsList = goodsEbi.getAllByGtm(goodsTypeList.get(0).getUuid());
		return "ajaxGetGtmBySm";
	}

	public String ajaxGetGmByGtm() {
		goodsList  = goodsEbi.getAllByGtm(gtmUuid);
		for(int i= goodsList.size()-1;i>=0;i--) {
			if(gmUuids.contains("'"+goodsList.get(i).getUuid()+"'")) {
				goodsList.remove(i);
			}
		}
		return "ajaxGetGmByGtm";
	}

	public String ajaxGetGm() {
		gm  = goodsEbi.getByUuid(gmUuid);
		return "ajaxGetGm";
	}

	public Long storageUuid;
	public Long odmUuid;
	public Integer inNum;

	public OrderDetailModel getOdm() {
		return odm;
	}

	private OrderDetailModel odm;

	//-------------storageAjax-----------------
	public String ajaxIn() {
		//仓库里存进去 gm.uuid + storageUuid
		odm = orderEbi.store(storageUuid,odmUuid,inNum,getLogin());
		//odm odm surPlus减少
		//log 记录
		//
		return "ajaxIn";
	}

	public void out() {
		orderEbi.out(odmUuid,getLogin());
	}
}
