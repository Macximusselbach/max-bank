package br.com.selbach.MaxBank.exception;

public class CpfAlreadyExistsException extends RuntimeException {

    public CpfAlreadyExistsException() {
        super("Cpf já cadastrado!");

    }
}
