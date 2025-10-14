package br.com.vittalis.sistema.controller;

import br.com.vittalis.sistema.model.Administrador;
import br.com.vittalis.sistema.repository.AdministradorRepository;
import br.com.vittalis.sistema.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String administrador() {
        return "home/home-administrador";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/listagem")
    public String listagem(Model model) {

        List<Administrador> listaAdministrador = administradorRepository.findAll();

        model.addAttribute("clientes", listaAdministrador);

        return "pages/adm/listagem";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Administrador administrador
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "pages/adm/cadastro";
        }

        administrador.getUser().setPassword(bCryptPasswordEncoder.encode(administrador.getSenha()));
        administrador.getUser().setUsername(administrador.getEmail());
        administrador.addRole(roleRepository);

        administradorRepository.save(administrador);
        return "redirect:/login";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @Valid Administrador administrador
            , BindingResult result
            , RedirectAttributes attributes
    ) {
        if(result.hasErrors()) {
            return "pages/adm/alterar" + administrador.getId();
        }
        administradorRepository.save(administrador);
        return "redirect:/administrador";
    }



    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {
        Optional<Administrador> administradorOptional = administradorRepository.findById(id);

        if (administradorOptional.isPresent()) {
            Administrador administrador = administradorOptional.get();
            model.addAttribute("administrador", administrador);
            return "pages/adm/alterar";
        } else {
            return "redirect:/adm/listagem";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        administradorRepository.deleteById(id);
        return "redirect:/administrador";
    }


    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Administrador administrador = new Administrador();
        model.addAttribute("administrador", administrador);
        return "pages/adm/cadastro";
    }



    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "pages/adm/listagem";
        }

        List<Administrador> administradoresBuscados = administradorRepository.findAdministradorByNomeCompletoContaining(administrador());
        model.addAttribute("administradores", administradoresBuscados);
        return "pages/adm/listagem";
    }

}
