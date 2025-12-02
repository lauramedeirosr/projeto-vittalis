package br.com.vittalis.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @GetMapping
    public String checkin(Model model) {
        // Dados de exemplo para as reservas. Substitua pela sua lógica de busca no banco de dados.
        List<ReservaExemplo> listaDeReservas = Arrays.asList(
                new ReservaExemplo("Paris", LocalDate.of(2023, 10, 15), "/img/paris.jpg"),
                new ReservaExemplo("Nova York", LocalDate.of(2023, 11, 20), "/img/ny.jpg"),
                new ReservaExemplo("Tóquio", LocalDate.of(2023, 12, 5), "/img/tokyo.jpg")
        );

        // O nome do atributo DEVE ser "reservas", como esperado pelo HTML.
        model.addAttribute("reservas", listaDeReservas);

        return "pages/cliente/checkin";
    }

    /**
     * Classe de exemplo para representar os dados que a página precisa.
     * Os nomes dos campos (destino, data, imagemUrl) correspondem ao que está no HTML.
     */
    public static class ReservaExemplo {
        private String destino;
        private LocalDate data;
        private String imagemUrl;

        public ReservaExemplo(String destino, LocalDate data, String imagemUrl) {
            this.destino = destino;
            this.data = data;
            this.imagemUrl = imagemUrl;
        }

        // Getters
        public String getDestino() { return destino; }
        public LocalDate getData() { return data; }
        public String getImagemUrl() { return imagemUrl; }
    }
}
