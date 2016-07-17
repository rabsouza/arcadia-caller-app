package br.com.battista.arcadiacaller.exception;


public class ArcadiaCallerException extends RuntimeException {

    public ArcadiaCallerException(String message) {
        super(message);
    }

    public ArcadiaCallerException(String message, Throwable cause) {
        super(message, cause);
    }
}
