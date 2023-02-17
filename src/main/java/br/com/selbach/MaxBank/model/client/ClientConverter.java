package br.com.selbach.MaxBank.model.client;

import br.com.selbach.MaxBank.entity.client.ClientEntity;

public class ClientConverter {

    public Client convertToModel(ClientEntity clientEntity) {
        return new Client(clientEntity.getId() , clientEntity.getName(), clientEntity.getEmail(), clientEntity.getCpf(), clientEntity.getBalance());

    }

    public ClientEntity convertToEntity(Client client) {
        return new ClientEntity(client.getName(), client.getEmail(), client.getCpf(), client.getBalance());

    }
}
