package com.carme.common.framework.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<PK, T extends Serializable> {

    /**
     * 根据id获得对象
     */
    T getById(PK id);

    /**
     * 根据id列表获得对象
     * @param idList
     * @return
     */
    List<T> getByIds(List<PK> idList);

    /**
     * 基础对象查询
     * @param query
     * @return
     */
    List<T> query(T query);

    /**
     * 保存对象
     */
    int save(T entity);

    /**
     * 批量保存对象
     */
    int saveBatch(List<T> entity);

    /**
     * 更新对象
     */
    int update(T entity);

    /**
     * 批量保存对象
     * @param entity
     * @return
     */
    int updateBatch(List<T> entity);

    /**
     * 根据主键id删除对象
     */
    int deleteById(PK id);

    /**
     * 根据主键id批量删除对象
     */
    int deleteByIds(List<PK> idList);

    /**
     * 根据主键id逻辑删除对象
     * @param id
     * @return
     */
    int logicDelById(PK id);

    /**
     * 根据主键id批量逻辑删除对象
     * @param idList
     */
    int logicDelByIds(List<PK> idList);
}
