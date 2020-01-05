package org.magnum.dataup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "video not found")
public class FileNotFoundException extends RuntimeException {

    String[] args;

    public FileNotFoundException(Exception ex) {
        super(ex);
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Exception ex) {
        super(message, ex);
    }

    public FileNotFoundException(String message, String... args) {
        super(message);
        this.args = args;
    }
}
