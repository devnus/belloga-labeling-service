package com.devnus.belloga.labeling.common.exception.error;

public class NotFoundDataException  extends RuntimeException {
    public NotFoundDataException() {
        super();
    }
    public NotFoundDataException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundDataException(String message) {
        super(message);
    }
    public NotFoundDataException(Throwable cause) {
        super(cause);
    }
}
