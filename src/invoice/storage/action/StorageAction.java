package invoice.storage.action;

import java.util.List;

import auth.emp.business.ebi.EmpEbi;
import auth.emp.vo.EmpModel;
import invoice.storage.business.ebi.StorageEbi;
import invoice.storage.vo.StorageModel;
import invoice.storage.vo.StorageQueryModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("storageAction")
@Scope("prototype")
public class StorageAction extends BaseAction{
	public StorageModel sm = new StorageModel();
	public StorageQueryModel sqm = new StorageQueryModel();

	@Resource
	private StorageEbi storageEbi;
	@Resource
	private EmpEbi empEbi;

	//列表
	public String list(){
		setTotalCount(storageEbi.getCount(sqm));
		List<StorageModel> storageList = storageEbi.getAll(sqm,pageNum,pageCount);
		put("storageList", storageList);
		return LIST;
	}

	//到添加
	public String input(){
		List<EmpModel> empList = empEbi.getAllInGroup(getLogin());
		put("empList",empList);
		if(sm.getUuid()!=null){
			sm = storageEbi.getByUuid(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			storageEbi.save(sm);
		}else{
			storageEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		storageEbi.delete(sm);
		return TO_LIST;
	}

}
