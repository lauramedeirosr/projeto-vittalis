package br.com.vittalis.sistema.config;

import br.com.vittalis.sistema.model.Role;
import br.com.vittalis.sistema.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityDataInitializer {

    @Bean
    public CommandLineRunner seedDefaultRoles(RoleRepository roleRepository) {
        return args -> {
            ensureRoleExists(roleRepository, "ADMINISTRADOR");
            ensureRoleExists(roleRepository, "CLIENTE");
            ensureRoleExists(roleRepository, "GERENTE");
        };
    }

    private void ensureRoleExists(RoleRepository roleRepository, String roleName) {
        Role existing = roleRepository.findByName(roleName);
        if (existing == null) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}