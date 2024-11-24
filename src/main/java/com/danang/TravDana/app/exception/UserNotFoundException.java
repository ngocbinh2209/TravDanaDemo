package com.danang.TravDana.app.exception;


public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super("User not found");
    }
}
