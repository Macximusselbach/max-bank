package br.com.selbach.MaxBank.model.client;

import br.com.selbach.MaxBank.entity.client.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientConverter {

    public Client convertToModel(ClientEntity clientEntity) {
        return new Client(clientEntity.getId() , clientEntity.getName(), clientEntity.getEmail(), clientEntity.getCpf(), clientEntity.getBalance());

    }

    public List<Client> convertListToModel(List<ClientEntity> clientEntityList) {
        List<Client> clientList = new ArrayList();

        clientEntityList.forEach(client -> clientList.add(new Client(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getBalance()
        )));

        return clientList;
    }

    public ClientEntity convertToEntity(Client client) {
        return new ClientEntity(client.getName(), client.getEmail(), client.getCpf(), client.getBalance());

    }
}
