package com.banorte.saldos.movimientos.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1234567L;
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable causa) {
        super(message, causa);
    }
    
}