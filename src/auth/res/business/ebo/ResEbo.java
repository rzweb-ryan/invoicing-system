package auth.res.business.ebo;

import auth.res.business.ebi.ResEbi;
import auth.res.dao.dao.ResDao;
import auth.res.vo.ResModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseQueryModel;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
@Service("resEbi")
@Transactional
public class ResEbo implements ResEbi {
    @Resource
    private ResDao resDao;

    public List<ResModel> getAll() {
        return resDao.getAll();
    }


    public void save(ResModel resModel) {
        resDao.save(resModel);
    }


    public ResModel getByUuid(Serializable uuid) {
        return resDao.get(uuid);
    }


    public void update(ResModel resModel) {
        resDao.update(resModel);
    }


    public void delete(ResModel resModel) {
        resDao.delete(resModel);

    }


    public List<ResModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        return resDao.getAll(bqm,pageNum,pageCount);
    }


    public Integer getCount(BaseQueryModel bqm) {
        return resDao.getCount(bqm);
    }

    public List<ResModel> getResByEmpUuid(Long uuid) {
        return resDao.getResByEmpUuid(uuid);
    }
}
