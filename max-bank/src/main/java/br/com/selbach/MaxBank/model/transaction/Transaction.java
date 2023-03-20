package br.com.selbach.MaxBank.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long id;

    private String senderName;

    private String senderEmail;

    private String senderCpf;

    private Double value;

    private String pixKey;

    private String receiverName;

    private String receiverEmail;

    private String receiverCpf;

    private String date;

}
