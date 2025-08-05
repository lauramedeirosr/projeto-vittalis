package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
public class Pacote {

    @Id
    @GeneratedValue
    private long id;
    private String nomeDestino;
    private String descricaoPacote;
    private double preco;
    private int vagasDisponiveis;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataLimiteInscricao;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataExpedicao;

}
