package com.pay.common.core.service.impl;


import com.pay.common.client.constants.ZookeeperCommonNamespace;
import com.pay.common.client.exception.PayException;
import com.pay.common.core.service.IIdService;
import com.pay.common.core.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * @author chenwei
 * @date 2019/2/19 16:18
 */
@Slf4j
public class IdServiceImpl implements IIdService {

    private static final Integer WORKER_ID_MAX = 36;

    private SnowflakeIdWorker snowflakeIdWorker;

    public IdServiceImpl(CuratorFrameworkFactory.Builder curatorFrameworkBuilder) {
        try {
            CuratorFramework curatorFramework = curatorFrameworkBuilder.build();
            curatorFramework.start();

            //使用zookeeper临时顺序节点生成worker id。0~31
            String baseKeyPath = ZookeeperCommonNamespace.WORKER_ID;
            if (curatorFramework.checkExists().forPath(baseKeyPath) == null) {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(baseKeyPath);
            }

            Integer workerId = null;
            List<String> workerIds = curatorFramework.getChildren().forPath(baseKeyPath);
            log.info("已有workerIds:{}", workerIds);
            if (workerIds != null && workerIds.size() > 0) {
                if (workerIds.size() > WORKER_ID_MAX) {
                    log.error("workerId数量已达到上限：{}", WORKER_ID_MAX);
                    throw new IllegalStateException("workerId数量已达到上限：" + WORKER_ID_MAX);
                }
                for (int i = 0; i <= WORKER_ID_MAX; i++) {
                    if (!workerIds.contains(String.valueOf(i))) {
                        workerId = i;
                        break;
                    }
                }
            } else {
                workerId = 0;
            }
            String fullPath = baseKeyPath + "/" + workerId;
            curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(fullPath);

            snowflakeIdWorker = new SnowflakeIdWorker(workerId, 1L);
            log.info("当前服务workerId:{}", workerId);
            final PathChildrenCache cache = new PathChildrenCache(curatorFramework, baseKeyPath, false);
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    case CHILD_REMOVED:
                        //判断是否是本节点
                        if (StringUtils.equals(fullPath, event.getData().getPath())) {
                            //重新创建连接
                            log.info("workerId节点:{}连接断开，开始重新连接...", fullPath);
                            CuratorFramework reconnectClient = curatorFrameworkBuilder.build();
                            reconnectClient.start();
                            reconnectClient.create()
                                    .creatingParentsIfNeeded()
                                    .withMode(CreateMode.EPHEMERAL)
                                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                                    .forPath(fullPath);
                            log.info("workerId节点:{}连接成功", fullPath);
                        }
                        break;
                    default:
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new PayException("初始化workerId失败");
        }
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
}
