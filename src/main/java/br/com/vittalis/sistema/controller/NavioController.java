package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Navio;
import br.com.vittalis.sistema.model.Quarto;
import br.com.vittalis.sistema.repository.NavioRepository;
import br.com.vittalis.sistema.repository.QuartoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/navio")
public class NavioController {

    @Autowired
    private NavioRepository navioRepository;
    @Autowired
    private QuartoRepository quartoRepository;


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


    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Navio navioOptional = navioRepository.findById(id).get();
        model.addAttribute("navio", navioOptional);

        return "pages/navio/alterar";
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


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
        if (navioRepository.existsById(id)) {
            navioRepository.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Navio excluído com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagemErro", "Navio não encontrado para exclusão!");
        }
        return "redirect:/navio";
    }

    @PostMapping("/addQuarto")
    public String addQuarto(Navio navio, Model model) {
        navio.addQuarto(new Quarto());
        List<Quarto> listaQuartos = quartoRepository.findAll();
        model.addAttribute("quartos", listaQuartos);
        return "pages/navio/cadastro :: quartos";
    }


    @PostMapping("/removerQuarto")
    public String removerQuarto(Navio navio, @RequestParam("rmQuarto") int index, Model model) {
        navio.getQuartos().remove(index);
        List<Quarto> listaQuartos = quartoRepository.findAll();
        model.addAttribute("quartos", listaQuartos);
        return "pages/navio/cadastro :: quartos";
    }


}