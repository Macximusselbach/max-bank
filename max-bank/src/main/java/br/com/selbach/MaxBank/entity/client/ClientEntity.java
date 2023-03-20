package br.com.selbach.MaxBank.entity.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @NonNull
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    @NonNull
    private String email;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @NonNull
    private String cpf;

    @Column(name = "balance", nullable = false)
    @NonNull
    private Double balance;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate = LocalDate.now();

}
