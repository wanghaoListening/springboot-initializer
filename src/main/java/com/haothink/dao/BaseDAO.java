package com.haothink.dao;

/**
 * @author wanghao
 * @date 2019年06月18日 19:01
 * description:
 */
public interface BaseDAO<T> {

    /**
     * 根据主键删除数据.
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     */
    int insert(T record);

    /**
     * 插入所选数据.
     */
    int insertSelective(T record);

    /**
     * 根据主键查询数据.
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据所选条件更新数据.
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新数据.
     */
    int updateByPrimaryKey(T record);

}
