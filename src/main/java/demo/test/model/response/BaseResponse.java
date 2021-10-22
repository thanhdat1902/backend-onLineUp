package demo.test.model.response;

import demo.test.util.TimeUtils;

public class BaseResponse<T> {
    public String code;
    public String desc;
    public long created_time;
    public T data;

    public BaseResponse() {
        this.created_time = TimeUtils.getCurrentTimestamp();
    }

    public BaseResponse(String code, String desc, T data) {
        this.data = data;
        this.code = code;
        this.desc = desc;
        this.created_time = TimeUtils.getCurrentTimestamp();
    }

    public BaseResponse(T data) {
        this.created_time = TimeUtils.getCurrentTimestamp();
        this.data = data;
    }
}

