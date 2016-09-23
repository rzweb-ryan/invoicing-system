package utils.base;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Created by RZ on 6/28/16.
 */
public abstract class BaseImpl<T> implements BaseDao<T> {
    
    @Resource
    protected HibernateTemplate hibernateTemplate;
    private Class < T >  entityClass;
    //get class of T
    public BaseImpl() {
        entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
    }
    public void save(T t) {
        hibernateTemplate.save(t);
    }

    public void delete(T t) {
        hibernateTemplate.delete(t);
    }

    public void update(T t) {
        hibernateTemplate.update(t);
    }

    public T get(Serializable uuid) {
        return hibernateTemplate.get(entityClass,uuid);
    }

    public List<T> getAll() {
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
        return hibernateTemplate.findByCriteria(dc);
    }

    public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
        doCriteria(dc,bqm);
        return hibernateTemplate.findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
    }

    public Integer getCount(BaseQueryModel bqm) {
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
        dc.setProjection(Projections.rowCount());
        doCriteria(dc,bqm);
        List<Long> res =  hibernateTemplate.findByCriteria(dc);
        return res.get(0).intValue();
    }

    /**
     * Qbc program
     * @param dc
     * @param bqm
     */
    public abstract void doCriteria(DetachedCriteria dc,BaseQueryModel bqm);
}
