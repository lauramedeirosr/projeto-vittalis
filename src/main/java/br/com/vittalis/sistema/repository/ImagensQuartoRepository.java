package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.ImagensQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagensQuartoRepository extends JpaRepository<ImagensQuarto, Long> {
    // Métodos personalizados podem ser adicionados aqui se necessário
}

