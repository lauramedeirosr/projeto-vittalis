package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Quarto;
import br.com.vittalis.sistema.repository.QuartoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private QuartoRepository quartoRepository;

    @GetMapping
    public String listagem(Model model) {

        List<Quarto> listaQuartos = quartoRepository.findAll();

        model.addAttribute("quartos", listaQuartos);

        return "pages/quarto/listagem";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Quarto quarto
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "redirect:/quarto/cadastro";
        }
        quartoRepository.save(quarto);
        return "redirect:/quarto";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Quarto quarto = new Quarto();
        model.addAttribute("quarto", quarto);
        return "pages/quarto/cadastro";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Quarto quarto = quartoRepository.findById(id).get();
        model.addAttribute("quarto", quarto);
        return "pages/quarto/alterar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        quartoRepository.deleteById(id);
        return "redirect:/quarto";
    }

    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "pages/quarto/listagem";
        }

        List<Quarto> quartosBuscados = quartoRepository.findQuartoByNomeContaining(nome);
        model.addAttribute("quartos", quartosBuscados);
        return "pages/quarto/listagem";
    }

}
