package parking.mock.server.service;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryService {
    public static Observable<Object> retry(Observable<Throwable> errors, Class exceptionType) {
        return errors
                .flatMap(it -> {
                    return Observable.error(new Exception("thorw out"));
                })
                .zipWith(Observable.range(1, 3), (e, i) -> i)
                .flatMap(retryCount -> {
                    return Observable
                            .timer(1L, TimeUnit.SECONDS)
                            .doOnNext(it -> {
                                log.info("retrying " + retryCount);
                            });
                });

    }

    public static String testcall() throws Exception {
        System.out.println("testcall");

//        return "returncall";
        throw new Exception("customexception");
    }

    public static void main(String[] args) {
        Observable<String> stringObservable = Observable.fromCallable(() -> testcall());
        stringObservable
                .retryWhen((exception) -> retry(exception, RuntimeException.class))
                .doOnError(error -> {
                    error.printStackTrace();
                })
                .blockingSubscribe(it -> {
                    log.info(it);
                });
    }
}
