package dev.auma.secure_api.exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
