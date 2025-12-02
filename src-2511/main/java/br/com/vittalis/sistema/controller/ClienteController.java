package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Cliente;
import br.com.vittalis.sistema.model.User;
import br.com.vittalis.sistema.repository.ClienteRepository;
import br.com.vittalis.sistema.repository.RoleRepository;
import br.com.vittalis.sistema.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    // Injeção de dependência do repositório de clientes
    @Autowired
    private ClienteRepository clienteRepository;

    // Injeção de dependência do codificador de senhas BCrypt
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Injeção de dependência do repositório de papéis (roles)
    @Autowired
    private RoleRepository roleRepository;

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Método para listar todos os clientes
    @GetMapping
    public String listagem(Model model) {

        List<Cliente> listaClientes = clienteRepository.findAll();

        model.addAttribute("clientes", listaClientes);

        return "pages/cliente/listagem-cliente";
    }

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
            return "pages/cliente/cadastro-cliente";
        }

        cliente.getUser().setPassword(bCryptPasswordEncoder.encode(cliente.getSenha()));
        cliente.getUser().setUsername(cliente.getEmail());
        cliente.addRole(roleRepository);

        clienteRepository.save(cliente);

        attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!!");


        return "redirect:/login";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @Valid Cliente cliente
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "pages/cliente/alterar-cliente" + cliente.getId();
        }

        attributes.addFlashAttribute("mensagem", "Cadastro alterado com sucesso!!");


        clienteRepository.save(cliente);
        return "redirect:/cliente";
    }



    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).get();
        model.addAttribute("cliente", cliente);
        return "pages/cliente/alterar-cliente";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes, ModelMap modelMap) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteRepository.findById(id).get();
            User user = cliente.getUser();
            user.setRoles(new ArrayList<>());
            userRepository.save(user);


            clienteRepository.deleteById(id);
            attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Usuário não encontrado!");
        }
        return "redirect:/cliente";
    }


    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "pages/cliente/cadastro-cliente";
    }



    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "pages/cliente/listagem-cliente";
        }

        List<Cliente> clientesBuscados = clienteRepository.findClienteByNomeCompletoContaining(nome);
        model.addAttribute("clientes", clientesBuscados);
        return "pages/cliente/listagem-cliente";
    }
}
