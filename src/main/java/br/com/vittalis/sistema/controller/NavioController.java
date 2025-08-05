package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Navio;
import br.com.vittalis.sistema.repository.NavioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/navio")
public class NavioController {

    @Autowired
    private NavioRepository navioRepository;


    @GetMapping
    public String listagem(Model model) {
        List<Navio> listaNavios = navioRepository.findAll();
        model.addAttribute("navios", listaNavios);
        return "pages/navio/listagem";
    }


    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Navio navio = new Navio();
        model.addAttribute("navio", navio);
        return "pages/navio/cadastro";
    }


    @PostMapping("/salvar")
    public String salvar(
            @Valid Navio navio,
            BindingResult result,
            RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao salvar o navio. Verifique os campos.");
            attributes.addFlashAttribute("navio", navio);
            return "pages/navio/cadastro";
        }
        navioRepository.save(navio);
        attributes.addFlashAttribute("mensagemSucesso", "Navio salvo com sucesso!");
        return "redirect:/navio";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Optional<Navio> navioOptional = navioRepository.findById(id);
        if (navioOptional.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Navio não encontrado para edição!");
            return "redirect:/navio";
        }
        model.addAttribute("navio", navioOptional.get());
        return "pages/navio/cadastro";
    }


    @PostMapping("/atualizar")
    public String atualizar(
            @Valid Navio navio,
            BindingResult result,
            RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao atualizar o navio. Verifique os campos.");
            attributes.addFlashAttribute("navio", navio);
            return "pages/navio/cadastro";
        }


        navioRepository.save(navio);
        attributes.addFlashAttribute("mensagemSucesso", "Navio atualizado com sucesso!");
        return "redirect:/navio";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
        if (navioRepository.existsById(id)) {
            navioRepository.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Navio excluído com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagemErro", "Navio não encontrado para exclusão!");
        }
        return "redirect:/navio";
    }
}