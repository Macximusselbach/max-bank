package br.com.selbach.MaxBank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Getter @Setter
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    @Getter @Setter
    private String email;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @Getter @Setter
    private String cpf;

    @Column(name = "balance", nullable = false)
    @Getter @Setter
    private Double balance = 0.0;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate = LocalDate.now();

}
