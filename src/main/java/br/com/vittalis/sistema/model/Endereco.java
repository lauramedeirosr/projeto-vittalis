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
    public class Endereco {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty(message = "O campo 'CEP' deve ser preenchido")
        private String cep;

        @NotEmpty(message = "O campo 'Rua' deve ser preenchido")
        private String rua;

        private String complemento;

        @NotEmpty(message = "O campo 'Bairro' deve ser preenchido")
        private String bairro;

        @NotEmpty(message = "O campo 'Cidade' deve ser preenchido")
        private String cidade;

        @NotEmpty(message = "O campo 'Número' deve ser preenchido")
        private String numero;

        @NotEmpty(message = "O campo 'UF' deve ser preenchido")
        private String uf;
    }

