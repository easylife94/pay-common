package com.pay.common.core.service;

/**
 * Id服务接口
 *
 * @author chenwei
 * @date 2019/1/30 13:57
 */
public interface IIdService {

    /**
     * 创建全局唯一long类型id
     *
     * @return
     */
    Long generateId();

    /**
     * 创建全局唯一订单号
     *
     * @param prefix 订单号前缀
     * @return 返回全局唯一订单号
     */
    String generateOrderNumber(String prefix);

    /**
     * 设置workerId
     *
     * @param workerId
     */
    void setWorkerId(Integer workerId);
}
