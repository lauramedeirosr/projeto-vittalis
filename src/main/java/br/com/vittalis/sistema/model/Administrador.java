package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.RoleRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@DiscriminatorValue(value = "A")
@Entity
public class Administrador extends Pessoa {

    public static final String ROLE_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
    public void addRole(RoleRepository roleRepository) {
        Role role = roleRepository.findByName(ROLE_ADMINISTRADOR);
        this.getUser().getRoles().add(role);
    }
  
}
