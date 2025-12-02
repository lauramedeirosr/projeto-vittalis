package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Administrador;
import br.com.vittalis.sistema.model.Cliente;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    List<Administrador> findAdministradorByNomeCompletoContaining(@NotEmpty(message = "O campo 'Nome' deve ser preenchido.") String nomeCompleto);
}
