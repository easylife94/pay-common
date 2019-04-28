package com.pay.common.core.service;

import com.pay.common.client.dto.WorkerPageDTO;

import java.util.List;

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

    /**
     * worker同时对一个数据集操作需要做分页处理。
     *
     * @param total 待处理数据集总数
     * @return 分页DTO
     */
    WorkerPageDTO workerPage(Long total);
}
