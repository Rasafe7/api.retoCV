package com.example.demo.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String mensaje){
        super(mensaje);
    }

}
