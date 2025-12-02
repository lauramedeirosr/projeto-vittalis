package br.com.vittalis.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acesso-negado")
public class AcessoNegadoController {

    @GetMapping
    public String acessoNegado() {
        return "error/403";
    }
}