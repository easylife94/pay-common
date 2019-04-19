package com.pay.common.client.model;

import lombok.Data;

/**
 * 去重表抽象类
 *
 * @author chenwei
 * @date 2019/4/19 09:59
 */
@Data
public class AbstractUniqueBaseDO {

    /**
     * 唯一key
     */
    private String uniqueKey;

    /**
     * 创建时间戳
     */
    private Long createTime;

    /**
     * 根据唯一key构造记录
     *
     * @param uniqueKey 唯一key
     */
    public AbstractUniqueBaseDO(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.createTime = System.currentTimeMillis();
    }
}
