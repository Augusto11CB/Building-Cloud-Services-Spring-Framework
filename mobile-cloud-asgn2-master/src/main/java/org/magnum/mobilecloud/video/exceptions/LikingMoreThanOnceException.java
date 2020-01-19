package org.magnum.mobilecloud.video.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "video not found")
public class LikingMoreThanOnceException extends RuntimeException {

    String[] args;

    public LikingMoreThanOnceException(Exception ex) {
        super(ex);
    }

    public LikingMoreThanOnceException(String message) {
        super(message);
    }

    public LikingMoreThanOnceException(String message, Exception ex) {
        super(message, ex);
    }

    public LikingMoreThanOnceException(String message, String... args) {
        super(message);
        this.args = args;
    }
}
