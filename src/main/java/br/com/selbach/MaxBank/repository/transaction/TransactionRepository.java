package br.com.selbach.MaxBank.repository.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findBySenderCpf(String senderCpf);

    TransactionEntity findByReceiverCpf(String receiverCpf);


}
