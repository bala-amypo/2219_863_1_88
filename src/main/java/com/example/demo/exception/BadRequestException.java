package com.example.demo.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestexception(String message){
        super(message);
    }
}