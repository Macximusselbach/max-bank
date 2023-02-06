package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.entity.ClientEntity;
import br.com.selbach.MaxBank.model.client.Client;

public class ClientConverter {

    public Client convertToModel(ClientEntity client) {

        return new Client(
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getBalance()
        );

    }
}
