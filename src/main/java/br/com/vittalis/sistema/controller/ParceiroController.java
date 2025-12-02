package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Parceiro;
import br.com.vittalis.sistema.repository.ParceiroRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parceiro")
@RequiredArgsConstructor // IMPORTANTE: O Lombok cria o construtor para campos 'final'
public class ParceiroController {

    // CORREÇÃO: Adicionado 'final'. Sem isso, o repository fica null e dá Erro 500.
    private final ParceiroRepository parceiroRepository;

    // Rota: /parceiro/listar
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Parceiro> parceiros = parceiroRepository.findAll();
        model.addAttribute("parceiros", parceiros);
        return "pages/parceiro/listagem"; // Caminho: src/main/resources/templates/parceiro/listagem.html
    }

    // Rota: /parceiro/cadastro
    @GetMapping("/cadastro")
    public String cadastrar(Model model) {
        model.addAttribute("parceiro", new Parceiro());
        return "pages/parceiro/cadastro";
    }

    // Rota: /parceiro/salvar
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("parceiro") Parceiro parceiro,
                         BindingResult result,
                         RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "pages/parceiro/cadastro";
        }

        parceiroRepository.save(parceiro);
        attributes.addFlashAttribute("mensagem", "Parceiro salvo com sucesso!");
        return "redirect:/parceiro/listar";
    }

    // Rota: /parceiro/buscar
    @GetMapping("/buscar")
    public String buscar(@RequestParam("nome") String nome, Model model) {
        List<Parceiro> parceiros = parceiroRepository.findByNomeResponsavelContainingIgnoreCase(nome);
        model.addAttribute("parceiros", parceiros);
        return "pages/parceiro/listagem";
    }

    // Rota: /parceiro/alterar/{id}
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Optional<Parceiro> parceiroOpt = parceiroRepository.findById(id);

        if (parceiroOpt.isPresent()) {
            model.addAttribute("parceiro", parceiroOpt.get());
            return "pages/parceiro/cadastro";
        } else {
            attributes.addFlashAttribute("mensagem", "Parceiro não encontrado.");
            return "redirect:/parceiro/listar";
        }
    }

    // Rota: /parceiro/excluir/{id}
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            parceiroRepository.deleteById(id);
            attributes.addFlashAttribute("mensagem", "Parceiro excluído com sucesso.");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagem", "Não é possível excluir: O parceiro possui vínculos no sistema.");
        }
        return "redirect:/parceiro/listar";
    }
}