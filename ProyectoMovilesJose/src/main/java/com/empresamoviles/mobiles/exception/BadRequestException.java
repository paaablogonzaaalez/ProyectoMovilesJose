package com.empresamoviles.mobiles.exception;

/**
 * Excepción lanzada cuando la petición contiene datos inválidos o incoherentes.
 * Mapeada a HTTP 400 por el GlobalExceptionHandler.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
