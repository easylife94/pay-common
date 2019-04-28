package com.pay.common.core.service.impl;


import com.pay.common.core.service.IIdService;
import com.pay.common.core.service.IWorkerService;
import com.pay.common.core.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenwei
 * @date 2019/2/19 16:18
 */
@Slf4j
public class IdServiceImpl implements IIdService {

    private static final Integer WORKER_ID_MAX = 36;

    private SnowflakeIdWorker snowflakeIdWorker;

    private final IWorkerService workerService;

    public IdServiceImpl(IWorkerService workerService) {
        this.workerService = workerService;
        snowflakeIdWorker = new SnowflakeIdWorker(workerService.workerId(), 1L);
    }

    @Override
    public Long generateId() {
        return snowflakeIdWorker.nextId();
    }

    @Override
    public String generateOrderNumber(String prefix) {
        return new StringBuilder(prefix).append(generateId()).toString();
    }

    @Override
    public void setWorkerId(Integer workerId) {
        snowflakeIdWorker.setWorkerId(workerId);
    }

    @Override
    public Long getWorkerId() {
        return snowflakeIdWorker.getWorkerId();
    }
}
