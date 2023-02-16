package br.com.selbach.MaxBank.controller.client;

import br.com.selbach.MaxBank.TestBase;
import br.com.selbach.MaxBank.entity.client.ClientEntity;
import br.com.selbach.MaxBank.service.client.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
class ClientControllerTest extends TestBase {

    ClientEntity client1 = new ClientEntity();
    ClientEntity client2 = new ClientEntity();

    private String jsonClient = "{\"id\":1,\"name\":\"Teste\",\"email\":\"teste@gmail.com\",\"cpf\":\"12345678910\",\"balance\":0.0}";

    @MockBean
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ClientController clientController;

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
        client2.setCpf("123456789");
        client2.setBalance(0.0);

    }

    @Test
    void shouldReturnOkStatusAndClientList_whenGetAll() throws Exception {
        when(clientService.getAll()).thenReturn(List.of(client1));

        mockMvc.perform(get("/client"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Teste','email':'teste@gmail.com','cpf':'12345678910','balance':0.0}]"));

    }

    @Test
    void shouldReturnInternalServerErrorStatusAndCantConnectMessage_whenGetAll() throws Exception {
        when(clientService.getAll()).thenThrow(new NullPointerException());

        mockMvc.perform(get("/client"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Não foi possível conectar ao banco de dados!"));

    }

    @Test
    void shouldReturnOkStatusAndClient_whenGetById() throws Exception {
        when(clientService.getById(1L)).thenReturn(client1);

        mockMvc.perform(get("/client/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Teste','email':'teste@gmail.com','cpf':'12345678910','balance':0.0}"));

    }

    @Test
    void shouldReturnNotFoundStatus_whenGetById() throws Exception {
        when(clientService.getById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/client/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldReturnCreatedStatusAndCreatedClient_whenPost() throws Exception {
        when(clientService.post(Mockito.any())).thenReturn(client1);

        mockMvc.perform(post("/client")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonClient)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':1,'name':'Teste','email':'teste@gmail.com','cpf':'12345678910','balance':0.0}"));

    }

    @Test
    void shouldReturnNotAcceptableStatus_whenPost() throws Exception {
        when(clientService.post(Mockito.any())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/client")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonClient)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    void shouldreturnOkStatusAndModifiedClient_whenPut() throws Exception {
        client1.setName("testeNovo");

        when(clientService.put(Mockito.any())).thenReturn(client1);

        mockMvc.perform(put("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"testeNovo\",\"email\":\"teste@gmail.com\",\"cpf\":\"12345678910\",\"balance\":0.0}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'testeNovo','email':'teste@gmail.com','cpf':'12345678910','balance':0.0}"));

    }

    @Test
    void shouldReturnNotfoundStatus_whenPut() throws Exception {
        when(clientService.put(Mockito.any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClient)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldReturnOkStatusAndDeletedMessage_whenDelete() throws Exception {
        when(clientService.delete(1L)).thenReturn("Cliente deletado com sucesso!");

        mockMvc.perform(delete("/client/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente deletado com sucesso!"));


    }

    @Test
    void shouldReturnNotfoundStatus_whenDelete() throws Exception {
        when(clientService.delete(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/client/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}