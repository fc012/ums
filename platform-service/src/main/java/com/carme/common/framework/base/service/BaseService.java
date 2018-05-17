package com.carme.common.framework.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/4/19.
 */
public interface BaseService<PK, T extends Serializable> {
    /**
     * 根据id获得对象
     */
    T getById(PK id);

    /**
     * 根据id列表获得对象
     * @param id
     * @return
     */
    List<T> getByIds(List<PK> id);

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
     * @param entityList
     */
    int saveBatch(List<T> entityList);

    /**
     * 更新对象
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 批量更新对象
     * @param entityList
     * @return
     */
    int updateBatch(List<T> entityList);

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
