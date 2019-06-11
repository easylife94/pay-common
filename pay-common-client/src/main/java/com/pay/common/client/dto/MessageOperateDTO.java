package com.pay.common.client.dto;

import lombok.Data;

/**
 * 消息处理DTO
 *
 * @author chenwei
 * @date 2019/6/11 17:03
 */
@Data
public class MessageOperateDTO {

    /**
     * 消息处理器
     */
    private String operator;

    /**
     * 消息数据
     */
    private String data;

    /**
     *
     */
    private String clazz;
}
