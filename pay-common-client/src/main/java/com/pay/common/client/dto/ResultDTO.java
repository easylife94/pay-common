package com.pay.common.client.dto;

import lombok.Data;

/**
 * 结果
 *
 * @author chenwei
 * @date 2019/6/11 17:49
 */
@Data
public class ResultDTO {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 提示信息
     */
    private String message;

}
