package demo.test.model.response;

import demo.test.util.TimeUtils;

public class BaseResponse<T> {
    public String code;
    public String desc;
    public boolean status;
    public long created_time;
    public T data;

    public static <TT> BaseResponse Builder() {
        return new BaseResponse<TT>();
    }

    public BaseResponse addStatus(boolean status) {
        this.status = status;
        return this;
    }

    public BaseResponse addCode(String code) {
        this.code = code;
        return this;
    }

    public BaseResponse addDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public BaseResponse addData(T data) {
        this.data = data;
        return this;
    }

    public BaseResponse build() {
        return this;
    }

    public BaseResponse() {
        this.created_time = TimeUtils.getCurrentTimestamp();
    }
}

