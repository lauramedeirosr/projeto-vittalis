package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class Pacote {

    @Id
    @GeneratedValue
    private long id;
    private String nomePacote;
    private String descricaoPacote;
    private String navio;
    private String destino;
    private int vagasDisponíveis;



}
