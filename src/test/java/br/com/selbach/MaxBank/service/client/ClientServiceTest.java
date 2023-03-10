package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.TestBase;
import br.com.selbach.MaxBank.entity.client.ClientEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.CpfAlreadyExistsException;
import br.com.selbach.MaxBank.exception.EmailAlreadyExistsException;
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

    ClientEntity client1 = new ClientEntity();
    ClientEntity client2 = new ClientEntity();
    List<ClientEntity> clientList = new ArrayList<>();

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @BeforeEach
    public void setup() {

        client1.setId(1L);
        client1.setName("Teste");
        client1.setEmail("teste@gmail.com");
        client1.setCpf("12345678910");
        client1.setBalance(0.0);

        client2.setId(2L);
        client2.setName("Teste2");
        client2.setEmail("teste@gmail.com");
        client2.setCpf("12345678910");
        client2.setBalance(0.0);

        clientList.add(client1);
        clientList.add(client2);

    }

    @Test
    void shouldReturnClientList_whenFindAll() {
        when(clientRepository.findAll()).thenReturn(clientList);

        var result = clientService.getAll();

        verify(clientRepository).findAll();

        assertEquals(clientList, result);

    }

    @Test
    void shouldReturnClient_whenGetById(){
        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client1));

        var result = clientService.getById(1L);

        verify(clientRepository).findById(1L);

        assertEquals(client1, result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenGetByIdWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.getById(1L));

        assertEquals("Cliente n??o encontrado!", result.getMessage());

    }

    @Test
    void shouldReturnCreatedClient_whenPost() {
        when(clientRepository.save(client1)).thenReturn(client1);

        var result = clientService.post(client1);

        verify(clientRepository).save(client1);

        assertEquals(client1, result);

    }

    @Test
    void shouldReturnCpfAlreadyExistsException_whenPostWithExistingCpf() {
        when(clientRepository.findByCpf(client1.getCpf())).thenReturn(client1);

        var result = assertThrows(CpfAlreadyExistsException.class, () -> clientService.post(client1));

        assertEquals("Cpf j?? cadastrado!", result.getMessage());

    }

    @Test
    void shouldReturnEmailAlreadyExistsException_whenPostWithExistingEmail() {
        when(clientRepository.findByEmail(client1.getEmail())).thenReturn(client1);

        var result = assertThrows(EmailAlreadyExistsException.class, () -> clientService.post(client1));

        assertEquals("Email j?? cadastrado!", result.getMessage());
    }

    @Test
    void shouldReturnModifiedClient_whenPut() {
        client1.setEmail("teste01@gmail.com");

        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client1));
        when(clientRepository.save(client1)).thenReturn(client1);

        var result = clientService.put(client1);

        verify(clientRepository).save(client1);

        assertEquals(client1, result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenPutWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.put(client1));

        assertEquals("Cliente n??o encontrado!", result.getMessage());

    }

    @Test
    void shouldReturnDeletedMessage_whenDelete() {
        when(clientRepository.findById(2L)).thenReturn(Optional.ofNullable(client2));

        var result = clientService.delete(2L);

        verify(clientRepository).deleteById(2L);

        assertEquals("Cliente deletado com sucesso!", result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenDeleteWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.delete(client1.getId()));

        assertEquals("Cliente n??o encontrado!", result.getMessage());

    }
}