package auth.res.dao.dao;

import auth.res.vo.ResModel;
import auth.role.vo.RoleModel;
import utils.base.BaseDao;

import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
public interface ResDao extends BaseDao<ResModel> {
    public List<ResModel> getResByEmpUuid(Long uuid);
}
