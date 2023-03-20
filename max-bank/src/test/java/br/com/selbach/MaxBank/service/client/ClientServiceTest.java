package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.TestBase;
import br.com.selbach.MaxBank.entity.client.ClientEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.CpfAlreadyExistsException;
import br.com.selbach.MaxBank.exception.EmailAlreadyExistsException;
import br.com.selbach.MaxBank.model.client.Client;
import br.com.selbach.MaxBank.model.client.ClientConverter;
import br.com.selbach.MaxBank.repository.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceTest extends TestBase {

    ClientEntity client1Entity = new ClientEntity();
    ClientEntity client2Entity = new ClientEntity();

    Client client3 = new Client();
    ClientEntity client3Entity = new ClientEntity();
    Client client4 = new Client();
    ClientEntity client4Entity = new ClientEntity();
    List<Client> clientList = new ArrayList();
    List<ClientEntity> clientEntityList = new ArrayList();

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientConverter clientConverter;

    @InjectMocks
    ClientService clientService;

    @BeforeEach
    public void setup() {

        client1Entity.setId(1L);
        client1Entity.setName("Teste1");
        client1Entity.setEmail("teste1@gmail.com");
        client1Entity.setCpf("12345678910");
        client1Entity.setBalance(0.0);

        client2Entity.setId(2L);
        client2Entity.setName("Teste2");
        client2Entity.setEmail("teste2@gmail.com");
        client2Entity.setCpf("12345678910");
        client2Entity.setBalance(0.0);

        client3.setId(3L);
        client3.setName("Teste3");
        client3.setEmail("teste3@gmail.com");
        client3.setCpf("12345678910");
        client3.setBalance(0.0);

        client4.setId(4L);
        client4.setName("Teste4");
        client4.setEmail("teste4@gmail.com");
        client4.setCpf("12345678910");
        client4.setBalance(0.0);

        client3Entity.setId(3L);
        client3Entity.setName("Teste3");
        client3Entity.setEmail("teste3@gmail.com");
        client3Entity.setCpf("12345678910");
        client3Entity.setBalance(0.0);

        client4Entity.setId(4L);
        client4Entity.setName("Teste4");
        client4Entity.setEmail("teste4@gmail.com");
        client4Entity.setCpf("12345678910");
        client4Entity.setBalance(0.0);

        clientList.add(client3);
        clientList.add(client4);

        clientEntityList.add(client3Entity);
        clientEntityList.add(client4Entity);

    }

    @Test
    void shouldReturnClientList_whenFindAll() {
        when(clientRepository.findAll()).thenReturn(clientEntityList);
        when(clientConverter.convertListToModel(clientEntityList)).thenReturn(clientList);

        var result = clientService.findAll();

        verify(clientRepository).findAll();
        verify(clientConverter).convertListToModel(clientEntityList);

        assertEquals(clientList, result);

    }

    @Test
    void shouldReturnClient_whenFindById(){
        when(clientRepository.findById(3L)).thenReturn(Optional.ofNullable(client3Entity));
        when(clientConverter.convertToModel(client3Entity)).thenReturn(client3);

        var result = clientService.findById(3L);

        verify(clientRepository).findById(3L);
        verify(clientConverter).convertToModel(client3Entity);

        assertEquals(client3, result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenGetByIdWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.findById(1L));

        assertEquals("Cliente não encontrado!", result.getMessage());

    }

    @Test
    void shouldReturnCreatedClient_whenSave() {
        when(clientRepository.save(client4Entity)).thenReturn(client4Entity);
        when(clientConverter.convertToModel(client4Entity)).thenReturn(client4);

        var result = clientService.save(client4Entity);

        verify(clientRepository).save(client4Entity);
        verify(clientConverter).convertToModel(client4Entity);

        assertEquals(client4, result);

    }

    @Test
    void shouldReturnCpfAlreadyExistsException_whenSaveWithExistingCpf() {
        when(clientRepository.findByCpf(client1Entity.getCpf())).thenReturn(client1Entity);

        var result = assertThrows(CpfAlreadyExistsException.class, () -> clientService.save(client1Entity));

        assertEquals("Cpf já cadastrado!", result.getMessage());

    }

    @Test
    void shouldReturnEmailAlreadyExistsException_whenSaveWithExistingEmail() {
        when(clientRepository.findByEmail(client1Entity.getEmail())).thenReturn(client1Entity);

        var result = assertThrows(EmailAlreadyExistsException.class, () -> clientService.save(client1Entity));

        assertEquals("Email já cadastrado!", result.getMessage());
    }

    @Test
    void shouldReturnModifiedClient_whenUpdate() {
        client3Entity.setEmail("teste03@gmail.com");

        when(clientRepository.findById(3L)).thenReturn(Optional.ofNullable(client3Entity));
        when(clientRepository.save(client3Entity)).thenReturn(client3Entity);
        when(clientConverter.convertToModel(client3Entity)).thenReturn(client3);

        var result = clientService.update(client3Entity);

        verify(clientRepository).save(client3Entity);
        verify(clientConverter).convertToModel(client3Entity);

        assertEquals(client3, result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenPutWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.update(client1Entity));

        assertEquals("Cliente não encontrado!", result.getMessage());

    }

    @Test
    void shouldReturnDeletedMessage_whenDelete() {
        when(clientRepository.findById(2L)).thenReturn(Optional.ofNullable(client2Entity));

        var result = clientService.delete(2L);

        verify(clientRepository).deleteById(2L);

        assertEquals("Cliente deletado com sucesso!", result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenDeleteWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.delete(client1Entity.getId()));

        assertEquals("Cliente não encontrado!", result.getMessage());

    }
}