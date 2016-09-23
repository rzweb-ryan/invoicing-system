package auth.dept.business.ebo;

import auth.dept.business.ebi.DeptEbi;
import auth.dept.dao.dao.DeptDao;
import auth.dept.vo.DeptModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseQueryModel;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
@Service("deptEbi")
@Transactional
public class DeptEbo implements DeptEbi {
    @Resource
    private DeptDao deptDao;

    public void save(DeptModel dm) {
        deptDao.save(dm);

    }

    public DeptModel getByUuid(Serializable uuid) {
        return deptDao.get(uuid);

    }

    public void update(DeptModel dm) {
        deptDao.update(dm);
    }

    public void delete(DeptModel dm) {
        deptDao.delete(dm);
    }

    public List<DeptModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        return deptDao.getAll(bqm,pageNum,pageCount);
    }

    public Integer getCount(BaseQueryModel bqm) {
        return deptDao.getCount(bqm);

    }

    public List<DeptModel> getAll() {
        return deptDao.getAll();
    }

}
