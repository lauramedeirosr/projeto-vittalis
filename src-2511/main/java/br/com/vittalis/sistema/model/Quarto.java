package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo 'Nome do quarto' deve ser preenchido")
    private String nome;

    @NotEmpty(message = "O campo 'Descrição' deve ser preenchido")
    private String descricao;

    private Long quantidadeDisponivel;



}
