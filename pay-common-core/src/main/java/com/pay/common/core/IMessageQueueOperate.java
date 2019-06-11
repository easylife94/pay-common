package com.pay.common.core;

import com.pay.common.client.dto.MessageOperateDTO;
import com.pay.common.client.dto.MessageOperateResultDTO;

/**
 * 消息队列处理器接口
 *
 * @author chenwei
 * @date 2019/6/11 17:43
 */
public interface IMessageQueueOperate {

    MessageOperateResultDTO operate(MessageOperateDTO operateDTO);
}
