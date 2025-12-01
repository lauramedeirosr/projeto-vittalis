package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Parceiro;
import br.com.vittalis.sistema.repository.ParceiroRepository;
import br.com.vittalis.sistema.service.ParceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parceiro")
@RequiredArgsConstructor
public class ParceiroController {

    @Autowired
    private ParceiroRepository parceiroRepository;

    // 1. Listar Parceiros (Página Inicial)
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Parceiro> parceiros = parceiroRepository.findAll();
        model.addAttribute("parceiros", parceiros);
        return "parceiro/listagem";
        // Certifique-se que o HTML está em: src/main/resources/templates/parceiro/listagem.html
    }

    // 2. Abrir Formulário de Cadastro (Novo)
    @GetMapping("/cadastro")
    public String cadastrar(Model model) {
        model.addAttribute("parceiro", new Parceiro());
        return "parceiro/cadastro";
        // Certifique-se que o HTML está em: src/main/resources/templates/parceiro/cadastro.html
    }

    // 3. Salvar (Serve tanto para CRIAR quanto para ATUALIZAR)
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("parceiro") Parceiro parceiro,
                         BindingResult result,
                         RedirectAttributes attributes) {

        // Se houver erros no formulário (campos vazios, email inválido, etc)
        if (result.hasErrors()) {
            return "parceiro/cadastro";
        }

        // O Spring Data JPA verifica se o objeto tem ID:
        // Se ID for null -> Faz INSERT (Cria novo)
        // Se ID existir no banco -> Faz UPDATE (Atualiza os dados)
        parceiroRepository.save(parceiro);

        attributes.addFlashAttribute("mensagem", "Parceiro salvo com sucesso!");
        return "redirect:/parceiro/listar";
    }

    // 4. Buscar por Nome (Alterado para GET para evitar reenvio de formulário)
    @GetMapping("/buscar")
    public String buscar(@RequestParam("nome") String nome, Model model) {
        // Busca usando o método do Repository (IgnoreCase para ignorar maiúsculas/minúsculas)
        List<Parceiro> parceiros = parceiroRepository.findByNomeResponsavelContainingIgnoreCase(nome);

        model.addAttribute("parceiros", parceiros);
        return "parceiro/listagem";
    }

    // 5. Preencher formulário para Edição
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Optional<Parceiro> parceiroOpt = parceiroRepository.findById(id);

        if (parceiroOpt.isPresent()) {
            model.addAttribute("parceiro", parceiroOpt.get());
            return "parceiro/cadastro";
        } else {
            attributes.addFlashAttribute("mensagem", "Parceiro não encontrado.");
            return "redirect:/parceiro/listar";
        }
    }

    // 6. Excluir registro
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            parceiroRepository.deleteById(id);
            attributes.addFlashAttribute("mensagem", "Parceiro excluído com sucesso.");
        } catch (Exception e) {
            // Captura erros de integridade (ex: tentar apagar um parceiro vinculado a uma venda/pacote)
            attributes.addFlashAttribute("mensagem", "Não é possível excluir: O parceiro possui vínculos no sistema.");
        }
        return "redirect:/parceiro/listar";
    }
}
