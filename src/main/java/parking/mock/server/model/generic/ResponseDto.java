package parking.mock.server.model.generic;

public class ResponseDto<T> {

    public ResponseDto(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    private T data;
}
