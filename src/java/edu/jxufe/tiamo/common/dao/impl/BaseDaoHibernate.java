package edu.jxufe.tiamo.common.dao.impl;

import edu.jxufe.tiamo.common.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class BaseDaoHibernate<T> implements BaseDao<T> {

    private SessionFactory sessionFactory;
    // 自动注入会话仓库
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public T get(Class<T> entityClazz, Serializable id){
        return (T)getSessionFactory().getCurrentSession()
                .get(entityClazz,id);
    }

    @Override
    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession()
                .save(entity);
    }

    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession()
                .saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession()
                .delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(Class<T> entityClazz , Serializable id)
    {
        getSessionFactory().getCurrentSession().createQuery(String.format("delete from %s en where en.id = ?0", entityClazz.getSimpleName()))
                .setParameter("0", id)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Class<T> entityClazz){
        return find("select en from "+entityClazz.getSimpleName()+" en");
    }

    @Override
    public long findCount(Class<T> entityClazz){
        List l = find("select count(*) from "
                +entityClazz.getSimpleName());
        if(l!=null&&l.size()==1){
            return (Long)l.get(0);
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql){
        return (List<T>) getSessionFactory().getCurrentSession()
                .createQuery(hql)
                .list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql,Object... params){
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hql);
        for (int i = 0,len=params.length; i < len; i++) {
            query.setParameter(i+"",params[i]);
        }
        return (List<T>)query.list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql,int pageNo,int pageSize){
        return getSessionFactory().getCurrentSession()
                .createQuery(hql)
                .setFirstResult((pageNo-1)*pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql,int pageNo,int pageSize,Object... params){
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hql);
        for (int i = 0, len=params.length; i < len; i++) {
            query.setParameter(i+"",params[i]);
        }
        return query.setFirstResult((pageNo-1)*pageSize)
                .setMaxResults(pageSize)
                .list();
    }
}
