package com.pay.common.client.exception;

/**
 * 通用异常对象
 *
 * @author chenwei
 * @date 2019/4/17 16:05
 */
public class PayException extends RuntimeException {

    private static final long serialVersionUID = -1024123839167425243L;

    public PayException(String message) {
        super(message);
    }
}
