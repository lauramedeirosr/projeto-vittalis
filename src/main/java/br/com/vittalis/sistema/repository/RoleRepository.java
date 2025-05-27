package br.com.vittalis.sistema.repository;


import br.com.vittalis.sistema.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
