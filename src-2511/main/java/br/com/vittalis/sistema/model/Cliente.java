package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.RoleRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@DiscriminatorValue(value = "C")
@Entity
public class Cliente extends Pessoa{

    public static final String ROLE_CLIENTE = "CLIENTE";
    public void addRole(RoleRepository roleRepository) {
        Role role = roleRepository.findByName(ROLE_CLIENTE);
        this.getUser().getRoles().add(role);
    }



}
