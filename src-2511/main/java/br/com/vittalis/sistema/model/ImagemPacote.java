package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemPacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String URL;

    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;
    private Boolean destaque;
}
