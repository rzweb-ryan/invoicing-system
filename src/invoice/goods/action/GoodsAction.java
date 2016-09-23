package invoice.goods.action;

import java.util.List;

import invoice.goods.business.ebi.GoodsEbi;
import invoice.goods.vo.GoodsModel;
import invoice.goods.vo.GoodsQueryModel;
import invoice.goodsType.business.ebi.GoodsTypeEbi;
import invoice.goodsType.vo.GoodsTypeModel;
import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.vo.SupplierModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("goodsAction")
@Scope("prototype")
public class GoodsAction extends BaseAction{
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	@Resource
	private GoodsEbi goodsEbi;
	@Resource
	private SupplierEbi supplierEbi;
	@Resource
	private GoodsTypeEbi goodsTypeEbi;
	//列表
	public String list(){

		setTotalCount(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList", goodsList);
		List<SupplierModel> supplierList = supplierEbi.getAllUnion2();
		put("supplierList",supplierList);
		return LIST;
	}

	//到添加
	public String input(){
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList",supplierList);
		List<GoodsTypeModel> goodsTypeList = null;
		Long smUuid = null;
		if(gm!=null&&gm.getUuid()!=null) {
			gm = goodsEbi.getByUuid(gm.getUuid());
			smUuid = gm.getGtm().getSm().getUuid();
		}else {
			smUuid = supplierList.get(0).getUuid();
		}
		goodsTypeList = goodsTypeEbi.getAllBySupplier(smUuid);
		put("goodsTypeList",goodsTypeList);
		if(gm.getUuid()!=null){
			gm = goodsEbi.getByUuid(gm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(gm.getUuid() == null){
			goodsEbi.save(gm);
		}else{
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		goodsEbi.delete(gm);
		return TO_LIST;
	}

}
