package com.danang.TravDana.app.exception;

public class UserExistsException extends BaseException {
    public UserExistsException() {
        super("User already exists");
    }
}
