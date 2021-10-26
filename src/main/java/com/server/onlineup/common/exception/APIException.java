package com.server.onlineup.common.exception;

import com.server.onlineup.common.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public class APIException extends RuntimeException {
    private BaseResponse baseResponse;

    public APIException(BaseResponse baseResponse) {
        super();
        this.baseResponse = baseResponse;
    }

    public ResponseEntity<Object> getResponse() {
        return baseResponse.build();
    }
}
