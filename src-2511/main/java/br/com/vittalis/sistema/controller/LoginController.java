package br.com.vittalis.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
//Controlador responsável por gerenciar as requisições relacionadas ao login dos usuários.
public class LoginController {
    @GetMapping
    public String login(){
        return "home/login";
    }
}