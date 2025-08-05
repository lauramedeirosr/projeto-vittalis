package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Navio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavioRepository extends JpaRepository<Navio, Long> {
    List<Navio> findClienteBynomeNavioContaining(String nomeNavio);
}
