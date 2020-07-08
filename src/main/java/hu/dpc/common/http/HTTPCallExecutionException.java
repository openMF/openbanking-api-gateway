package hu.dpc.common.http;

public class HTTPCallExecutionException extends Exception {
    private static final long serialVersionUID = 4929101739214284784L;

    public HTTPCallExecutionException(final Throwable throwable) {
        super(throwable);
    }
}
