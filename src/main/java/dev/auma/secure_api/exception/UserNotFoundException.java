package dev.auma.secure_api.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
