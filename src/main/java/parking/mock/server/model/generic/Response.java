package parking.mock.server.model.generic;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;


public class Response<T> {

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    private T data;
}
