package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Parceiro;
import br.com.vittalis.sistema.repository.ParceiroRepository;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parceiro")
public class ParceiroController {

    @Autowired
    private ParceiroRepository parceiroRepository;

    @GetMapping
    public String listagem(Model model) {
        List<Parceiro> listaParceiros = parceiroRepository.findAll();
        model.addAttribute("parceiros", listaParceiros);
        return "pages/parceiro/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Parceiro parceiro = new Parceiro();
        model.addAttribute("parceiro", parceiro);
        return "pages/parceiro/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Parceiro parceiro,
            BindingResult result,
            RedirectAttributes attributes
    ) {
        if (result.hasErrors()) {
            return "redirect:/parceiro/cadastro";
        }
        parceiroRepository.save(parceiro);
        return "redirect:/parceiro";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Optional<Parceiro> opt = parceiroRepository.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/parceiro";
        }
        model.addAttribute("parceiro", opt.get());
        return "pages/parceiro/alterar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        parceiroRepository.deleteById(id);
        return "redirect:/parceiro";
    }

    @PostMapping("/buscar")
    public String buscar(Model model, @RequestParam("nome") String nomeParceiro) {
        if (nomeParceiro == null || nomeParceiro.trim().isEmpty()) {
            return "redirect:/parceiro";
        }

        List<Parceiro> parceirosBuscados = parceiroRepository.findByNomeParceiroContainingIgnoreCase(nomeParceiro);
        model.addAttribute("parceiros", parceirosBuscados);
        return "pages/parceiro/listagem";
    }
}
