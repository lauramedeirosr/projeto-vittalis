package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PacoteRepository pacoteRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("pacotes", pacoteRepository.findAll());
        model.addAttribute("MeuNome", "Laura");
        return "home/index";
    }

    @GetMapping("/contato")
    public String contato() {
        return "home/contato";
    }

    @GetMapping("/pacote")
        public String pacoteInfo(){
        return "home/info-pacote";
    }

    @GetMapping("/reserva")
        public String reserva() {
        return "pages/reserva/reserva-pacote";
    }


}
