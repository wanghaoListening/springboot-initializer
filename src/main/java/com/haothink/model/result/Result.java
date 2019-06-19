package com.haothink.model.result;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author wanghao
 * @date 2019年06月19日 18:13
 * description:
 */
public class Result<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -2058208145664510281L;
    private int resultCode;
    private String resultMsg;
    private boolean success;
    private T module;
    private Serializable debugInfo;
    public static int CODE_SUCCESS = 200;
    public static int CODE_FAILED = -1;
    public static String MSG_SUCCESS = "Success";

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static Result buildSuccessResult() {
        return new Result(CODE_SUCCESS, MSG_SUCCESS, true, (Serializable)null);
    }

    public static Result buildSuccessResult(Serializable module) {
        return new Result(CODE_SUCCESS, MSG_SUCCESS, true, module);
    }

    public static Result buildSuccessResult(Serializable module, String msg) {
        return new Result(CODE_SUCCESS, msg, true, module);
    }

    public static Result buildSuccessResult(Serializable module, String msg, Serializable debugInfo) {
        return new Result(CODE_SUCCESS, msg, true, module, debugInfo);
    }

    public static Result buildFailedResult(String errMsg) {
        return new Result(CODE_FAILED, errMsg, false, (Serializable)null);
    }

    public static Result buildFailedResult(String errMsg, int errCode) {
        return new Result(errCode, errMsg, false, (Serializable)null);
    }

    public static Result buildFailedResult(String errMsg, Serializable module, Serializable debugInfo) {
        return new Result(CODE_FAILED, errMsg, false, module, debugInfo);
    }

    public static Result buildFailedResultWithModule(String errMsg, Serializable module) {
        return new Result(CODE_FAILED, errMsg, false, module);
    }

    public static Result buildFailedResultWithDebugInfo(String errMsg, Serializable debugInfo) {
        return new Result(CODE_FAILED, errMsg, false, (Serializable)null, debugInfo);
    }

    public static Result buildFailedResult(String errMsg, String... infos) {
        return new Result(CODE_FAILED, errMsg + "|" + StringUtils.join(infos, "|"), false, (Serializable)null);
    }

    public Result() {
    }

    public Result(int resultCode, String resultMsg, boolean success, T module) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.module = module;
        this.success = success;
    }

    public Result(int resultCode, String resultMsg, boolean success, T module, Serializable debugInfo) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.success = success;
        this.module = module;
        this.debugInfo = debugInfo;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getModule() {
        return this.module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public Object getDebugInfo() {
        return this.debugInfo;
    }

    public void setDebugInfo(Serializable debugInfo) {
        this.debugInfo = debugInfo;
    }
}

