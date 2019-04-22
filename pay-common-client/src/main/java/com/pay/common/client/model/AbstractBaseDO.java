package com.pay.common.client.model;

import lombok.Data;

import java.util.Date;

/**
 * 模型基类
 *
 * @author chenwei
 * @date 2019/4/18 09:56
 */
@Data
public abstract class AbstractBaseDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

    /**
     * 是否假删除
     */
    private Boolean isDeleted;

    public AbstractBaseDO(Long id) {
        Date now = new Date();
        this.id = id;
        this.gmtCreate = now;
        this.gmtUpdate = now;
        this.isDeleted = false;
    }

    public AbstractBaseDO() {
    }
}
