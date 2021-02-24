package com.hs.vueblog.common.Result;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result success() {
        return success(ResultCode.SUCCESS, "成功", null);
    }

    public static Result success(Object data) {
        return success(ResultCode.SUCCESS, "操作成功", data);
    }

    public static Result success(Map<String, Object> data) {
        return success(ResultCode.SUCCESS, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public static Result fail(String msg) {
        return fail(ResultCode.ERROR, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(ResultCode.ERROR, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
