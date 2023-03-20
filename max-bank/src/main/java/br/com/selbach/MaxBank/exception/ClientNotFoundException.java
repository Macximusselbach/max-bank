package br.com.selbach.MaxBank.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Cliente não encontrado!");

    }
}
