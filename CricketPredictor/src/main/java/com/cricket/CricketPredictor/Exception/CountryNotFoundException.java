package com.cricket.CricketPredictor.Exception;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message){
        super(message);
    }
}
