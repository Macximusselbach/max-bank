package br.com.selbach.MaxBank.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    private Double balance;

}
