package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Cliente;
import br.com.vittalis.sistema.model.Pacote;
import br.com.vittalis.sistema.repository.PacoteRepository;
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
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @GetMapping
    public String listagem(Model model) {

        List<Pacote> listaPacotes = pacoteRepository.findAll();

        model.addAttribute("pacotes", listaPacotes);

        return "pages/pacote/listagem";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Pacote pacote
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "redirect:/pacote/cadastro";
        }
        pacoteRepository.save(pacote);
        return "redirect:/pacote";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Pacote pacote = new Pacote();
        model.addAttribute("pacote", pacote);
        return "pages/pacote/cadastro";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Pacote pacote = pacoteRepository.findById(id).get();
        model.addAttribute("pacote", pacote);
        return "pages/pacote/alterar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
       pacoteRepository.deleteById(id);
        return "redirect:/pacote";
    }

    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "pages/cliente/listagem-cliente";
        }

        List<Cliente> clientesBuscados = PacoteRepository.findPacoteByNomeCompletoContaining(nome);
        model.addAttribute("clientes", clientesBuscados);
        return "pages/cliente/listagem-cliente";
    }

}
