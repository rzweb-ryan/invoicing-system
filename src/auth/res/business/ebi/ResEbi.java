package auth.res.business.ebi;

import auth.res.vo.ResModel;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseEbi;

import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
@Transactional
public interface ResEbi extends BaseEbi<ResModel> {
    public List<ResModel> getResByEmpUuid(Long uuid);
}
