package com.pay.common.core.service.impl;

import com.pay.common.client.constants.ZookeeperCommonNamespace;
import com.pay.common.client.exception.PayException;
import com.pay.common.core.service.IWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * 基于zookeeper实现的worker协调服务
 *
 * @author chenwei
 * @date 2019/4/24 11:21
 */
@Slf4j
public class ZookeeperWorkerServiceImpl implements IWorkerService {

    private Integer workerId;

    private final CuratorFrameworkFactory.Builder curatorFrameworkBuilder;

    public ZookeeperWorkerServiceImpl(CuratorFrameworkFactory.Builder curatorFrameworkBuilder) {
        this.curatorFrameworkBuilder = curatorFrameworkBuilder;
        try {
            CuratorFramework curatorFramework = curatorFrameworkBuilder.build();
            curatorFramework.start();

            //使用zookeeper临时顺序节点生成worker id。0~31
            String baseKeyPath = ZookeeperCommonNamespace.WORKERS;
            if (curatorFramework.checkExists().forPath(baseKeyPath) == null) {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(baseKeyPath);
            }

            List<String> workerIds = curatorFramework.getChildren().forPath(baseKeyPath);
            log.info("已有workerIds:{}", workerIds);
            if (workerIds != null && workerIds.size() > 0) {
                for (int i = 0; ; i++) {
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

            log.info("当前服务workerId:{}", workerId);
            final PathChildrenCache cache = new PathChildrenCache(curatorFramework, baseKeyPath, false);
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    case CHILD_REMOVED:
                        //判断是否是本节点
                        if (StringUtils.equals(fullPath, event.getData().getPath())) {
                            //重新创建连接
                            log.info("worker节点:{}连接断开，开始重新连接...", fullPath);
                            CuratorFramework reconnectClient = curatorFrameworkBuilder.build();
                            reconnectClient.start();
                            reconnectClient.create()
                                    .creatingParentsIfNeeded()
                                    .withMode(CreateMode.EPHEMERAL)
                                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                                    .forPath(fullPath);
                            log.info("worker节点:{}连接成功", fullPath);
                        }
                        break;
                    default:
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new PayException("初始化worker节点失败");
        }
    }

    @Override
    public Integer count() {
        try {
            CuratorFramework curatorFramework = curatorFrameworkBuilder.build();
            curatorFramework.start();
            String baseKeyPath = ZookeeperCommonNamespace.WORKERS;
            if (curatorFramework.checkExists().forPath(baseKeyPath) != null) {
                List<String> workerIds = curatorFramework.getChildren().forPath(baseKeyPath);
                return workerIds.size();
            } else {
                throw new PayException("获取worker总数失败，worker节点不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PayException("获取worker总数异常");
        }
    }

    @Override
    public Integer workerId() {
        return workerId;
    }
}
