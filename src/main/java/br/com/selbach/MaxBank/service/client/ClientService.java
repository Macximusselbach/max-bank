package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.entity.ClientEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.CpfAlreadyExistsException;
import br.com.selbach.MaxBank.exception.EmailAlreadyExistsException;
import br.com.selbach.MaxBank.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<ClientEntity> findAll() {
        return clientRepository.findAll();

    }

    public ClientEntity post(ClientEntity client) throws CpfAlreadyExistsException, EmailAlreadyExistsException {
        Optional<ClientEntity> clientFromCpf = Optional.ofNullable(clientRepository.findByCpf(client.getCpf()));
        Optional<ClientEntity> clientFromEmail = Optional.ofNullable(clientRepository.findByEmail(client.getEmail()));

        if(clientFromCpf.isPresent()) {
            throw new CpfAlreadyExistsException();

        }

        if(clientFromEmail.isPresent()) {
            throw new EmailAlreadyExistsException();

        }

        return clientRepository.save(client);

    }

    public ClientEntity put(ClientEntity client) throws ClientNotFoundException {
        Optional<ClientEntity> clientToEdit = clientRepository.findById(client.getId());

        if (clientToEdit.isEmpty()) {
            throw new ClientNotFoundException();

        }

        return clientRepository.save(client);
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
