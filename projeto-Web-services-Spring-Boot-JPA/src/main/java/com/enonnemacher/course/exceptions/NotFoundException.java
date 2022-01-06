package com.enonnemacher.course.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Object id) {
        super("Not found. Id " + id);
    }
}
