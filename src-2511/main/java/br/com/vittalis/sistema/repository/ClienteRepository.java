package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findClienteByNomeCompletoContaining(String nome);
}
