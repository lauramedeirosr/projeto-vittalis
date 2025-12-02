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
public class Navio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "O campo 'Nome do navio' deve ser preenchido")
    private String nomeNavio;

    @NotEmpty(message = "O campo 'descrição do navio' deve ser preenchido")
    private String descricao;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "navio_id")
    private List<Quarto> quartos = new ArrayList<Quarto>();


    public void addQuarto(Quarto quarto){
        this.quartos.add(quarto);
    }

}
