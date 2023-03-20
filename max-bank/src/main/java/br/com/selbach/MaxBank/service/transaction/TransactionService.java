package br.com.selbach.MaxBank.service.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;
import br.com.selbach.MaxBank.exception.ClientNotFoundException;
import br.com.selbach.MaxBank.exception.ValueNotAcceptableException;
import br.com.selbach.MaxBank.model.client.Client;
import br.com.selbach.MaxBank.model.client.ClientConverter;
import br.com.selbach.MaxBank.model.transaction.Transaction;
import br.com.selbach.MaxBank.model.transaction.TransactionConverter;
import br.com.selbach.MaxBank.repository.transaction.TransactionRepository;
import br.com.selbach.MaxBank.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientConverter clientConverter;

    public List<Transaction> findTransactions(String cpf) {
        List<TransactionEntity> transactionsReceived = transactionRepository.findByReceiverCpf(cpf);
        List<TransactionEntity> transactionsSent = transactionRepository.findBySenderCpf(cpf);

        List<Transaction> transactions = new ArrayList();

        transactions.addAll(transactionConverter.converListToModel(transactionsReceived));
        transactions.addAll(transactionConverter.converListToModel(transactionsSent));

        return transactions;

    }

    public Transaction save(TransactionEntity transaction) throws ValueNotAcceptableException, ClientNotFoundException {
        Client clientSender = clientService.findByCpf(transaction.getSenderCpf());
        Client clientReceiver;

        if (transaction.getPixKey().contains("@")){
            clientReceiver = clientService.findByEmail(transaction.getReceiverEmail());

        } else {
            clientReceiver = clientService.findByCpf(transaction.getReceiverCpf());

        }

        if(transaction.getValue() == 0 || transaction.getValue() < 0) {
            throw new ValueNotAcceptableException("O valor deve ser maior que 0!");

        } else if(clientSender.getBalance() < transaction.getValue()) {
            throw new ValueNotAcceptableException("Saldo insuficiente!");

        }

        Double senderNewBalance = clientSender.getBalance() - transaction.getValue();
        Double receiverNewBalance = clientReceiver.getBalance() + transaction.getValue();

        clientSender.setBalance(senderNewBalance);
        clientReceiver.setBalance(receiverNewBalance);

        clientService.update(clientConverter.convertToEntity(clientSender));
        clientService.update(clientConverter.convertToEntity(clientReceiver));

        return transactionConverter.convertToModel(transactionRepository.save(transaction));

    }

}
