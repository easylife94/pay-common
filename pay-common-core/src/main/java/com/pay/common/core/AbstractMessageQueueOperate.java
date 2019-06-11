package com.pay.common.core;

import com.pay.common.client.dto.MessageOperateDTO;
import com.pay.common.client.dto.MessageOperateResultDTO;

/**
 * @author chenwei
 * @date 2019/6/11 17:56
 */
public abstract class AbstractMessageQueueOperate<T> implements IMessageQueueOperate {

    @Override
    public MessageOperateResultDTO operate(MessageOperateDTO operateDTO) {

        String clazz = operateDTO.getClazz();


        return null;
    }

    public abstract MessageOperateResultDTO realOperate();
}
