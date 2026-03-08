package com.gesiath.bugtrackerapi.exception;


public class CustomDataNotFoundException extends RuntimeException{


    public  CustomDataNotFoundException(){

        super();

    }

    public CustomDataNotFoundException(String message){

        super(message);

    }

}