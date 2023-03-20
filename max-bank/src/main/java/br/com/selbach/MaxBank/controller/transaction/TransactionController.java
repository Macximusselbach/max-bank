package br.com.selbach.MaxBank.controller.transaction;

import br.com.selbach.MaxBank.entity.transaction.TransactionEntity;
import br.com.selbach.MaxBank.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity getAll(@RequestBody String cpf) {

        try {
            return new ResponseEntity(transactionService.findTransactions(cpf), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity("[]", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping
    public ResponseEntity post(@RequestBody TransactionEntity transaction) {

        try {
            return new ResponseEntity(transactionService.save(transaction), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getStackTrace(), HttpStatus.NOT_ACCEPTABLE);

        }
    }

}