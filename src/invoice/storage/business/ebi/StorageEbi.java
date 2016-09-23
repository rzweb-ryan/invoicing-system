package invoice.storage.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import invoice.storage.vo.StorageModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface StorageEbi extends BaseEbi<StorageModel>{

    public List<StorageModel> getAllNotFull();
}
