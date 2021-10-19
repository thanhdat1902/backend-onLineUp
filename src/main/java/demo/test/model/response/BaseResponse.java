package demo.test.model.response;

public class BaseResponse<T> {
    public String code;
    public String desc;
    public long created_time;
    public T data;

    public BaseResponse(T request, long created_time) {
        data = request;
        this.created_time = created_time;
    }

}

