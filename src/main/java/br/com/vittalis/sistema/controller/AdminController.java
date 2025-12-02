package br.com.vittalis.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdminController {

    /**
     * Este método responde à URL /administrador e direciona para a página inicial do painel de administração.
     */
    @GetMapping
    public String adminHome() {
        return "pages/admin/home";
    }
}
