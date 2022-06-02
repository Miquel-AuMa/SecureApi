package dev.auma.secure_api.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
