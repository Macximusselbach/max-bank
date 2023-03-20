package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.entity.client.ClientEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.CpfAlreadyExistsException;
import br.com.selbach.MaxBank.exception.EmailAlreadyExistsException;
import br.com.selbach.MaxBank.model.client.Client;
import br.com.selbach.MaxBank.model.client.ClientConverter;
import br.com.selbach.MaxBank.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientConverter clientConverter;

    public List<Client> findAll() {
        List<ClientEntity> clientsFromDB = clientRepository.findAll();

        List<Client> clientList = clientConverter.convertListToModel(clientsFromDB);
        System.out.println(clientList);
        return clientList;

    }

    public Client findById(Long id) throws ClientNotFoundException {
        Optional<ClientEntity> clientFromId = clientRepository.findById(id);

        if(clientFromId.isEmpty()) {
            throw new ClientNotFoundException();

        }

        return clientConverter.convertToModel(clientFromId.get());

    }

    public Client findByEmail(String email) throws ClientNotFoundException {
        Optional<ClientEntity> clientFromEmail = Optional.ofNullable(clientRepository.findByEmail(email));

        if(clientFromEmail.isEmpty()) {
            throw new ClientNotFoundException();

        }

        return clientConverter.convertToModel(clientFromEmail.get());

    }

    public Client findByCpf(String cpf) throws ClientNotFoundException {
        Optional<ClientEntity> clientFromCpf = Optional.ofNullable(clientRepository.findByCpf(cpf));

        if(clientFromCpf.isEmpty()) {
            throw new ClientNotFoundException();

        }

        return clientConverter.convertToModel(clientFromCpf.get());

    }

    public Client save(ClientEntity client) throws CpfAlreadyExistsException, EmailAlreadyExistsException {
        Optional<ClientEntity> clientFromCpf = Optional.ofNullable(clientRepository.findByCpf(client.getCpf()));
        Optional<ClientEntity> clientFromEmail = Optional.ofNullable(clientRepository.findByEmail(client.getEmail()));

        if(clientFromCpf.isPresent()) {
            throw new CpfAlreadyExistsException();

        }

        if(clientFromEmail.isPresent()) {
            throw new EmailAlreadyExistsException();

        }

        client.setBalance(100.00);
        return clientConverter.convertToModel(clientRepository.save(client));

    }

    public Client update(ClientEntity client) throws ClientNotFoundException {
        Optional<ClientEntity> clientToEdit = clientRepository.findById(client.getId());

        if (clientToEdit.isEmpty()) {
            throw new ClientNotFoundException();

        }

        return clientConverter.convertToModel(clientRepository.save(client));
    }

    public String delete(Long id) throws ClientNotFoundException {
        Optional<ClientEntity> clientToDelete= clientRepository.findById(id);

        if (clientToDelete.isEmpty()) {
            throw new ClientNotFoundException();

        }

        clientRepository.deleteById(id);
        return "Cliente deletado com sucesso!";

    }
}
