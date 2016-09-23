package utils.base;

import auth.dept.vo.DeptModel;
import auth.dept.vo.DeptQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RZ on 6/28/16.
 */
@Transactional
public interface BaseEbi<T> {
    public List<T> getAll();

    public void save(T t);

    public T getByUuid(Serializable uuid);

    public void update(T t);

    public void delete(T t);

    public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount);

    public Integer getCount(BaseQueryModel bqm);
}
