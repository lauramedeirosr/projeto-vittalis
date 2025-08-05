package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Cliente;
import br.com.vittalis.sistema.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
    static List<Cliente> findPacoteByNomeCompletoContaining(String nome) {
        return List.of();
    }

}
