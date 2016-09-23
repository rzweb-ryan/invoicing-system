package invoice.storageLog.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import invoice.storageLog.vo.StorageLogModel;
import utils.base.BaseEbi;

@Transactional
public interface StorageLogEbi extends BaseEbi<StorageLogModel>{

}
