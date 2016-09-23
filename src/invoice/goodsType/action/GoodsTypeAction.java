package invoice.goodsType.action;

import java.util.List;

import invoice.goodsType.business.ebi.GoodsTypeEbi;
import invoice.goodsType.vo.GoodsTypeModel;
import invoice.goodsType.vo.GoodsTypeQueryModel;
import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.vo.SupplierModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("goodsTypeAction")
@Scope("prototype")
public class GoodsTypeAction extends BaseAction{
	public GoodsTypeModel gtm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	@Resource
	private GoodsTypeEbi goodsTypeEbi;
	@Resource
	private SupplierEbi supplierEbi;

	//列表
	public String list(){
		setTotalCount(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	//到添加
	public String input(){
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		if(gtm.getUuid()!=null){
			gtm = goodsTypeEbi.getByUuid(gtm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(gtm.getUuid() == null){
			goodsTypeEbi.save(gtm);
		}else{
			goodsTypeEbi.update(gtm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		goodsTypeEbi.delete(gtm);
		return TO_LIST;
	}

	/**
	 * ajax
	 */
	private List<GoodsTypeModel> goodsTypeList;
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public String ajaxGetBySupplier () {
		if (gtm != null && gtm.getSm() != null) {
			goodsTypeList = goodsTypeEbi.getAllBySupplier(gtm.getSm().getUuid());
			System.out.println(goodsTypeList.size());
		}
		return "ajaxGetBySupplier";
	}
}
