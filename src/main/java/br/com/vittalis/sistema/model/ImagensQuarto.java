package br.com.vittalis.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagensQuarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeArquivo;
    private String caminhoImagem;
    private String tipoMime;
    private Long tamanhoArquivo;
    
    // Construtor para facilitar criação
    public ImagensQuarto(String nomeArquivo, String caminhoImagem, String tipoMime, Long tamanhoArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.caminhoImagem = caminhoImagem;
        this.tipoMime = tipoMime;
        this.tamanhoArquivo = tamanhoArquivo;
    }
}
