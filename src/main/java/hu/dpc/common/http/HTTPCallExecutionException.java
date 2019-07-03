package hu.dpc.common.http;

public class HTTPCallExecutionException extends Exception {
    public HTTPCallExecutionException(final Throwable throwable) {
        super(throwable);
    }
}
