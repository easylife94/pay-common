package com.pay.common.client.constants;

/**
 * 支付渠道枚举类
 *
 * @author chenwei
 * @date 2019/2/14 13:38
 */
public enum DefrayalChannelEnum {
    /**
     * 支付宝
     */
    ALI("ALI", "支付宝"),

    /**
     * 微信
     */
    WECHAT("WECHAT", "微信"),

    /**
     * qq钱包
     */
    QQ("QQ", "QQ钱包"),

    /**
     * 京东钱包
     */
    JD("JD", "京东钱包"),

    /**
     * 百度钱包
     */
    BAIDU("BAIDU", "百度钱包"),

    /**
     * 银联
     */
    UNION("UNION", "银联"),

    /**
     * 线下
     */
    OFFLINE("OFFLINE","线下")
    ;

    private String type;
    private String name;

    DefrayalChannelEnum(String type, String name) {
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
    }}
