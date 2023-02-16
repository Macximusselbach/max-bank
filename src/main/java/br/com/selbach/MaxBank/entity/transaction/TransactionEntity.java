package br.com.selbach.MaxBank.entity.transaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;


    @Column(name = "sender_id")
    @Getter @Setter
    private Long senderId;

    @Column(name = "receiver_id")
    @Getter @Setter
    private Long receiverId;


}
