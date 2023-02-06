package br.com.selbach.MaxBank.service.client;

import br.com.selbach.MaxBank.TestBase;
import br.com.selbach.MaxBank.doubles.client.FakeClientDatabase;
import br.com.selbach.MaxBank.entity.ClientEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.CpfAlreadyExistsException;
import br.com.selbach.MaxBank.exception.EmailAlreadyExistsException;
import br.com.selbach.MaxBank.repository.ClientRepository;
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

    FakeClientDatabase fakeClientDatabase = new FakeClientDatabase();

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

        var result = clientService.findAll();

        verify(clientRepository).findAll();

        assertEquals(clientList, result);

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

        assertEquals("Cpf já cadastrado!", result.getMessage());

    }

    @Test
    void shouldReturnEmailAlreadyExistsException_whenPostWithExistingEmail() {
        when(clientRepository.findByEmail(client1.getEmail())).thenReturn(client1);

        var result = assertThrows(EmailAlreadyExistsException.class, () -> clientService.post(client1));

        assertEquals("Email já cadastrado!", result.getMessage());
    }

    @Test
    void shouldReturnModifiedClient_whenPut() {
        client1.setEmail("teste01@gmail.com");

        when(clientRepository.findById(client1.getId())).thenReturn(Optional.ofNullable(client1));
        when(clientRepository.save(client1)).thenReturn(client1);

        var result = clientService.put(client1);

        verify(clientRepository).save(client1);

        assertEquals(client1, result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenPutWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.put(client1));

        assertEquals("Cliente não encontrado!", result.getMessage());

    }

    @Test
    void shouldReturnDeletedMessage_whenDelete() {
        when(clientRepository.findById(client2.getId())).thenReturn(Optional.ofNullable(client2));

        var result = clientService.delete(client2.getId());

        verify(clientRepository).deleteById(client2.getId());

        assertEquals("Cliente deletado com sucesso!", result);

    }

    @Test
    void shouldReturnClientNotFoundException_whenDeleteWithNoExistingId() {
        var result = assertThrows(ClientNotFoundException.class, () -> clientService.delete(client1.getId()));

        assertEquals("Cliente não encontrado!", result.getMessage());

    }
}