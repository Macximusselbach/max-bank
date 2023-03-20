package br.com.selbach.MaxBank.controller.client;

import br.com.selbach.MaxBank.entity.client.ClientEntity;
import br.com.selbach.MaxBank.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity getAll() {

        try {
            return new ResponseEntity(clientService.findAll(), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity("[]", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        try {
            return new ResponseEntity(clientService.findById(id), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ClientEntity client) {

        try {
            return new ResponseEntity(clientService.save(client), HttpStatus.CREATED);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody ClientEntity client) {

        try {
            return new ResponseEntity(clientService.update(client), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return new ResponseEntity(clientService.delete(id), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

    }
}
