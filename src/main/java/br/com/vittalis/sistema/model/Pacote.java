package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "pacote", cascade = CascadeType.ALL)
    private List<ImagemPacote> imagemPacote = new ArrayList<>();

}
