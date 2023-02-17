package br.com.selbach.MaxBank.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String senderName;

    private String senderEmail;

    private String senderCpf;

    private String pixKey;

    private String receiverName;

    private String receiverEmail;

    private String receiverCpf;

    private String date;

}
