package com.infy.menu.exception;

// Exception class emptyException
public class Recipe_emptyException extends RuntimeException {
    private static final long serialVersionUID=2L;

    public Recipe_emptyException(String message){
        super(message);
    }
}
