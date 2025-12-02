package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Cliente;
import br.com.vittalis.sistema.model.Pacote;
import br.com.vittalis.sistema.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    List<Quarto> findQuartoByNomeContaining(String nome);
}