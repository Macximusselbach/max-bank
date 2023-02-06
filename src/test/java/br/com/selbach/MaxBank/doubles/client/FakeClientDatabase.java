package br.com.selbach.MaxBank.doubles.client;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class FakeClientDatabase {

    List<FakeClientEntity> fakeClients = List.of(
            new FakeClientEntity(1L, "test1", "test1@gmail.com", "12345"),
            new FakeClientEntity(2L, "test2", "test2@gmail.com", "54321")
    );

    public List<FakeClientEntity> findAll() {
        return fakeClients;

    }

    public FakeClientEntity findByCpf(String cpf) {
        FakeClientEntity clientFound = null;
        fakeClients.forEach(client -> {
            if(client.getCpf() == cpf) {
                clientFound.setId(client.getId());
                clientFound.setName(client.getName());
                clientFound.setEmail(client.getEmail());
                clientFound.setCpf(client.getCpf());

            }
        });

        if (clientFound != null) {
            return clientFound;
        } else {
            return null;

        }

    }

    public FakeClientEntity findByEmail(String email) {
        FakeClientEntity clientFound = null;
        fakeClients.forEach(client -> {
            if(client.getEmail() == email) {
                clientFound.setId(client.getId());
                clientFound.setName(client.getName());
                clientFound.setEmail(client.getEmail());
                clientFound.setCpf(client.getCpf());

            }
        });

        if (clientFound != null) {
            return clientFound;

        } else {
            return null;

        }

    }

    public FakeClientEntity save(){

        return null;
    }

}
