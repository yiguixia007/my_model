package com.ego.model.common.mo;


import com.ego.model.common.constant.CommonConstant;

import java.io.Serializable;

public class ResponseRMO implements Serializable{

    private static final long serialVersionUID = 1L;

    private int code;

    private Object data;

    private String msg="";

    public ResponseRMO(){

    }

    public boolean success() {
        return CommonConstant.RESPONSE_CODE_SUCCESS == code;
    }

    public ResponseRMO(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseRMO(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
