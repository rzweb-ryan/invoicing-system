package invoice.supplier.action;

import java.util.List;

import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.vo.SupplierModel;
import invoice.supplier.vo.SupplierQueryModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("supplierAction")
@Scope("prototype")
public class SupplierAction extends BaseAction{
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	@Resource
	private SupplierEbi supplierEbi;

	//列表
	public String list(){
		setTotalCount(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList", supplierList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = supplierEbi.getByUuid(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}

}
