package com.carme.common.framework.base.service;

import com.carme.common.framework.base.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/4/19.
 */
public abstract class BaseServiceImpl<PK, T extends Serializable> implements BaseService<PK, T> {

    public abstract BaseDao getDao();

    /**
     * 根据id获得对象
     */
    @Override
    public T getById(PK id) {
        return (T) getDao().getById(id);
    }

    /**
     * 根据id列表获得对象
     * @param id
     * @return
     */
    public List<T> getByIds(List<PK> idList) {
        return getDao().getByIds(idList);
    }

    /**
     * 查询列表
     * @param query
     * @return
     */
    public List<T> query(T query) {
        return getDao().query(query);
    }

    /**
     * 保存对象
     */
    @Override
    public int save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public int saveBatch(List<T> entityList) {
        return getDao().saveBatch(entityList);
    }

    @Override
    public int update(T entity) {
        return getDao().update(entity);
    }

    /**
     * @see com.carme.platform.base.dao.BaseDao#save(List)
     */
    @Override
    public int updateBatch(List<T> entityList) {
        return getDao().updateBatch(entityList);
    }

    /**
     * 根据主键id删除对象
     */
    public int deleteById(PK id) {
        return getDao().deleteById(id);
    }

    /**
     * 根据主键id批量删除对象
     */
    public int deleteByIds(List<PK> idList) {
        return getDao().deleteByIds(idList);
    }

    /**
     * 根据主键id逻辑删除对象
     * @param id
     * @return
     */
    public int logicDelById(PK id) {
        return getDao().logicDelById(id);
    }

    /**
     * 根据主键id批量逻辑删除对象
     * @param ids
     */
    public int logicDelByIds(List<PK> idList) {
        return getDao().logicDelByIds(idList);
    }

}
