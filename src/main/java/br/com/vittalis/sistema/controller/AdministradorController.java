package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @GetMapping
    public String administrador() {
        return "home/home-administrador";
    }

}
