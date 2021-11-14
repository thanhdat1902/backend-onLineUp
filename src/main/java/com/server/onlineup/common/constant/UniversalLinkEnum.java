package com.server.onlineup.common.constant;

public enum UniversalLinkEnum implements BaseEnum {
    INVALID("This universal link is invalid", "invalid_link"),
    SUCCESS("Get config from universal link successfully", "success");


    UniversalLinkEnum(String desc, String descCode) {
        this.desc = desc;
        this.code = descCode;
    }

    private String desc;
    private String code;
    private String extra;

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getExtra() {
        return this.extra;
    }

    @Override
    public void setExtra(String extra) {
        this.extra = extra;
    }
}