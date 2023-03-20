package br.com.selbach.MaxBank.entity.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_email", nullable = false)
    private String senderEmail;

    @Column(name = "sender_cpf", nullable = false)
    private String senderCpf;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "pix_key", nullable = false)
    private String pixKey;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_email", nullable = false)
    private String receiverEmail;

    @Column(name = "receiver_cpf", nullable = false)
    private String receiverCpf;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date = LocalDate.now();
}
