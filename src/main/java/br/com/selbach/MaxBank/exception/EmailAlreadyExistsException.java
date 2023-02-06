package br.com.selbach.MaxBank.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email já cadastrado!");

    }
}
