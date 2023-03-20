package br.com.selbach.MaxBank.model.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionConverter {

    public Transaction convertToModel(TransactionEntity transactionEntity) {
        return new Transaction(
                transactionEntity.getId(),
                transactionEntity.getSenderName(),
                transactionEntity.getSenderEmail(),
                transactionEntity.getSenderCpf(),
                transactionEntity.getValue(),
                transactionEntity.getPixKey(),
                transactionEntity.getReceiverName(),
                transactionEntity.getReceiverEmail(),
                transactionEntity.getReceiverCpf(),
                transactionEntity.getDate().toString());

    }

    public List<Transaction> converListToModel(List<TransactionEntity> transactionEntityList) {
        List<Transaction> transactionList = new ArrayList();

        transactionEntityList.forEach(transaction -> transactionList.add(new Transaction(
                transaction.getId(),
                transaction.getSenderName(),
                transaction.getSenderEmail(),
                transaction.getSenderCpf(),
                transaction.getValue(),
                transaction.getPixKey(),
                transaction.getReceiverName(),
                transaction.getReceiverEmail(),
                transaction.getReceiverCpf(),
                transaction.getDate().toString()
        )));

        return transactionList;

    }
}
