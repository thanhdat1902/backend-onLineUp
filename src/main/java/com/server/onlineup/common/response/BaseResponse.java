package com.server.onlineup.common.response;

import com.server.onlineup.common.constant.BaseEnum;
import com.server.onlineup.common.utils.TimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse<T> {
    public String descriptionCode;
    public String description;
    private HttpStatus errorStatus = null;
    public long created_time;
    public T data;


    public static <TT> BaseResponse Builder() {
        return new BaseResponse<TT>();
    }


    public BaseResponse addMessage(BaseEnum messageEnum) {
        this.description = messageEnum.getDesc();
        this.descriptionCode = messageEnum.getCode();
        return this;
    }

    public BaseResponse addData(T data) {
        this.data = data;
        return this;
    }

    public BaseResponse addErrorStatus(HttpStatus error) {
        this.errorStatus = error;
        return this;
    }

    public ResponseEntity build() {
        return new ResponseEntity<>(this, this.errorStatus != null ? this.errorStatus : HttpStatus.OK);
    }

    private BaseResponse() {
        this.created_time = TimeUtils.getCurrentTimestamp();
    }
}

