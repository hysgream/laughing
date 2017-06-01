package com.laughing2b.common;

import java.util.List;

/**
 * 所有数据库持久化操作超类
 */
public interface BaseMapper<T> {

    /**
     * 查询列表
     * @param entity
     * @return
     */
    List<T> queryList (T entity);

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    T queryById (String id);

    /**
     * 根据对象查询一条数据
     * @param entity
     * @return
     */
    T queryByEntity (T entity);

    /**
     * 执行插入操作
     * @param entity
     * @return
     */
    int insert (T entity);

    /**
     * 执行更新操作
     * @param entity
     * @return
     */
    int update (T entity);

    /**
     * 执行删除操作
     * @param entity
     * @return
     */
    int delete (T entity);
}
