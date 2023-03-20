package br.com.selbach.MaxBank.repository.client;

import br.com.selbach.MaxBank.entity.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByCpf(String cpf);

    ClientEntity findByEmail(String email);

}
