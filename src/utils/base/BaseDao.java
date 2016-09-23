package utils.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RZ on 6/27/16.
 */
public interface BaseDao<T> {
    public void save(T t);
    public void delete(T t);
    public void update(T t);
    public T get(Serializable uuid);
    public List<T> getAll();
    //get all data with query model
    public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount);
    public Integer getCount(BaseQueryModel bqm);
}
