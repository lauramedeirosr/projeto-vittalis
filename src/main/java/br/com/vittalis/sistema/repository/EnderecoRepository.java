package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Endereco;
import br.com.vittalis.sistema.model.Navio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
