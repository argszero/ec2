package org.argszero.ec2.common;

/**
 * Created by shaoaq on 10/30/14.
 */
public class Response {
    private boolean success;
    private Object data;
    private int code;

    public Response(boolean success, Object data) {
        this.success = success;
        this.code = success ? 200 : 500;
        this.data = data;
    }

    public Response(Object data, int code) {
        this.data = data;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
