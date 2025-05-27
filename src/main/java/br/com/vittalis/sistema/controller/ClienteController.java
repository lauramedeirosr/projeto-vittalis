package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Cliente;
import br.com.vittalis.sistema.repository.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Cliente cliente
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "redirect:/cliente/cadastro";
        }
        clienteRepository.save(cliente);
        return "redirect:/login";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @Valid Cliente cliente
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "redirect:/cliente/alterar/" + cliente.getId();
        }
        clienteRepository.save(cliente);
        return "redirect:/cliente";
    }



    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).get();
        model.addAttribute("cliente", cliente);
        return "pages/Cliente/alterar-cliente";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/cliente";
    }

    @GetMapping
    public String listagem(Model model) {

        List<Cliente> listaClientes = clienteRepository.findAll();

        model.addAttribute("clientes", listaClientes);

        return "pages/Cliente/listagem-cliente";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "pages/Cliente/cadastro-cliente";
    }

    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "pages/Cliente/listagem-cliente";
        }

        List<Cliente> clientesBuscados = clienteRepository.findClienteByNomeCompletoContaining(nome);
        model.addAttribute("clientes", clientesBuscados);
        return "pages/Cliente/listagem-cliente";
    }
}
