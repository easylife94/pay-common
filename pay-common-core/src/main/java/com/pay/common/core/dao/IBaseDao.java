package com.pay.common.core.dao;

/**
 * dao操作基类
 *
 * @author chenwei
 * @date 2019/4/18 09:59
 */
public interface IBaseDao<T> {

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增记录
     *
     * @param record 记录
     * @return 新增记录数
     */
    int insert(T record);

    /**
     * 新增记录，不插入为null的字段
     *
     * @param record 记录
     * @return 新增记录数
     */
    int insertSelective(T record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 记录
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据主键更新记录，不更新为null字段
     *
     * @param record 记录
     * @return 更新记录数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新记录
     *
     * @param record 记录
     * @return 更新记录数
     */
    int updateByPrimaryKey(T record);


}
