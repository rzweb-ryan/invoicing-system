package auth.dept.business.ebi;

import auth.dept.vo.DeptModel;
import auth.dept.vo.DeptQueryModel;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseEbi;

import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
@Transactional
public interface DeptEbi extends BaseEbi<DeptModel> {

}
