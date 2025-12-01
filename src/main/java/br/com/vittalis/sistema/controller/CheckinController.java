package br.com.vittalis.sistema.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @GetMapping
    public String checkin(Model model) {
        // Dados de exemplo para os cards (você pode substituir por dados reais do banco)
        List<Destino> destinos = Arrays.asList(
                new Destino("Paris", "2023-10-15", "/images/destino1.jpg"),
                new Destino("Nova York", "2023-11-20", "/images/destino2.jpg"),
                new Destino("Tóquio", "2023-12-05", "/images/destino3.jpg")
        );
        model.addAttribute("destinos", destinos);
        return "pages/cliente/checkin";
    }

    // Classe simples para representar um destino
    public static class Destino {
        private String nome;
        private String data;
        private String imagem;

        public Destino(String nome, String data, String imagem) {
            this.nome = nome;
            this.data = data;
            this.imagem = imagem;
        }

        // Getters
        public String getNome() { return nome; }
        public String getData() { return data; }
        public String getImagem() { return imagem; }
    }
}