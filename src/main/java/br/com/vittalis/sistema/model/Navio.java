package br.com.vittalis.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Navio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotEmpty(message = "O campo 'Nome do navio' deve ser preenchido")
    private String nomeNavio;

    @NotEmpty(message = "O campo 'descrição do navio' deve ser preenchido")
    private String descricao;


    private String quarto1;
    private String quarto2;
    private String quarto3;
}
