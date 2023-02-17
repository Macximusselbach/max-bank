package br.com.selbach.MaxBank.model.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;

public class TransactionConverter {

    public Transaction convertToModel(TransactionEntity transactionEntity) {
        return new Transaction(
                transactionEntity.getSenderName(),
                transactionEntity.getSenderEmail(),
                transactionEntity.getSenderCpf(),
                transactionEntity.getPixKey(),
                transactionEntity.getReceiverName(),
                transactionEntity.getReceiverEmail(),
                transactionEntity.getReceiverCpf(),
                transactionEntity.getDate().toString());

    }
}
