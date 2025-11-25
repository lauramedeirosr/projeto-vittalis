package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
    List<Parceiro> findByNomeParceiroContainingIgnoreCase(String nomeParceiro);
}
