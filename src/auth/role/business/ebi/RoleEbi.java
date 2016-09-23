package auth.role.business.ebi;

import auth.role.vo.RoleModel;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseEbi;

/**
 * Created by RZ on 7/1/16.
 */
@Transactional
public interface RoleEbi extends BaseEbi<RoleModel> {
    void update(RoleModel rm, Long[] resources);

    public void save(RoleModel rm, Long[] resources);

    public void update(RoleModel rm, Long[] resources, Long[] mmUuids);

    public void save(RoleModel rm, Long[] resources, Long[] mmUuids);
}
