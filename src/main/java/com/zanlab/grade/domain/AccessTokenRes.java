package com.zanlab.grade.domain;

public class AccessTokenRes {
    //获取到的凭证
    private String access_token;
    //凭证有效时间，单位：秒。目前是7200秒之内的值。
    private int expires_in;
    private int errcode;
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "AccessTokenRes{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
