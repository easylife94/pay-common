package com.pay.common.core.service;

/**
 * worker服务
 * 协调worker之前，例如获取worker总数
 *
 * @author chenwei
 * @date 2019/4/24 11:21
 */
public interface IWorkerService {

    /**
     * 获取worker总数
     *
     * @return worker总数
     */
    Integer count();

    /**
     * 获取workerId
     *
     * @return workerId
     */
    Integer workerId();

}
