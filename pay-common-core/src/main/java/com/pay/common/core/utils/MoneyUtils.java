package com.pay.common.core.utils;

import java.math.BigDecimal;

/**
 * 金额帮助类
 *
 * @author chenwei
 * @date 2019/5/15 10:22
 */
public class MoneyUtils {

    public static Long toCentValue(BigDecimal amount){
        return amount.multiply(new BigDecimal(100)).longValue();
    }

    public static void main(String[] args) {
        System.out.println(toCentValue(new BigDecimal("1.01")));
    }
}
