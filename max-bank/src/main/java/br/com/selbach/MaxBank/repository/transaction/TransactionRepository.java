package br.com.selbach.MaxBank.repository.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findBySenderCpf(String senderCpf);

    List<TransactionEntity> findByReceiverCpf(String receiverCpf);


}
