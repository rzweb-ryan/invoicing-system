package invoice.storageLog.action;

import java.util.List;

import invoice.storageLog.business.ebi.StorageLogEbi;
import invoice.storageLog.vo.StorageLogModel;
import invoice.storageLog.vo.StorageLogQueryModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("storageLogAction")
@Scope("prototype")
public class StorageLogAction extends BaseAction{
	public StorageLogModel sm = new StorageLogModel();
	public StorageLogQueryModel sqm = new StorageLogQueryModel();

	@Resource
	private StorageLogEbi storageLogEbi;

	//列表
	public String list(){
		setTotalCount(storageLogEbi.getCount(sqm));
		List<StorageLogModel> storageLogList = storageLogEbi.getAll(sqm,pageNum,pageCount);
		put("storageLogList", storageLogList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = storageLogEbi.getByUuid(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			storageLogEbi.save(sm);
		}else{
			storageLogEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		storageLogEbi.delete(sm);
		return TO_LIST;
	}

}
