package com.pay.common.client.constants;

/**
 * 支付类型枚举类
 *
 * @author chenwei
 * @date 2019/2/14 13:38
 */
public enum DefrayalTypeEnum {
    /**
     * 反扫
     */
    SCAN("SCAN", "反扫"),

    /**
     * 正扫
     */
    NATIVE("NATIVE", "正扫"),

    /**
     * app支付
     */
    APP("APP", "app支付"),

    /**
     * js api支付
     */
    JSAPI("JSAPI", "jsapi支付"),

    /**
     * H5支付
     */
    H5("H5", "h5支付"),

    /**
     * 固码支付
     */
    SOLIDCODE("SOLIDCODE", "固码"),

    /**
     * 银联 - 网银B2B
     */
    B2B("B2B", "网银B2B"),

    /**
     * 银联 - 网银B2C储蓄卡
     */
    B2C_DEBIT("B2C_DEBIT", "网银B2C储蓄卡"),

    /**
     * 银联 - 网银B2C信用卡
     */
    B2C_CREDIT("B2C_CREDIT","网银B2C信用卡"),
    ;


    private String type;
    private String name;

    DefrayalTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
