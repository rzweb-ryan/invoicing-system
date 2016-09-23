package invoice.storageDetail.action;

import java.util.List;

import invoice.storageDetail.business.ebi.StorageDetailEbi;
import invoice.storageDetail.vo.StorageDetailModel;
import invoice.storageDetail.vo.StorageDetailQueryModel;
import utils.base.BaseAction;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

@Controller("storageDetailAction")
@Scope("prototype")
public class StorageDetailAction extends BaseAction{
	public StorageDetailModel sm = new StorageDetailModel();
	public StorageDetailQueryModel sqm = new StorageDetailQueryModel();

	@Resource
	private StorageDetailEbi storageDetailEbi;

	//列表
	public String list(){
		setTotalCount(storageDetailEbi.getCount(sqm));
		List<StorageDetailModel> storageDetailList = storageDetailEbi.getAll(sqm,pageNum,pageCount);
		put("storageDetailList", storageDetailList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = storageDetailEbi.getByUuid(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			storageDetailEbi.save(sm);
		}else{
			storageDetailEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		storageDetailEbi.delete(sm);
		return TO_LIST;
	}

}
