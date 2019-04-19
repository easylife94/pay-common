package com.pay.common.core.dao;

/**
 * 去重表dao操作接口
 *
 * @author chenwei
 * @date 2019/4/19 10:01
 */
public interface IBaseUniqueDao<T> {

    /**
     * 判断主键是否存在
     *
     * @param uniqueKey
     * @return 大于0表示存在
     */
    int count(String uniqueKey);

    /**
     * 新增记录
     *
     * @param record 记录
     * @return 新增记录数
     */
    int insert(T record);


}
