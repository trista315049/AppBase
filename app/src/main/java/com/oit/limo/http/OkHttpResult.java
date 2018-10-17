package com.oit.limo.http;

/**
 * Created by trista on 2017/3/24.
 * 返回节点
 */

public class OkHttpResult<T> {

    /**
     * appMsg : string
     * code : 0
     * result : string
     * showMsg : string
     */

    private String statusMsg;
    private int code;
    private T result;
    private String clientMsg;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getClientMsg() {
        return clientMsg;
    }

    public void setClientMsg(String clientMsg) {
        this.clientMsg = clientMsg;
    }
}