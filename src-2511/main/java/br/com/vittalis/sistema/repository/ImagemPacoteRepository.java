package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.ImagemPacote;
import br.com.vittalis.sistema.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemPacoteRepository extends JpaRepository<ImagemPacote, Long> {
    List<ImagemPacote> findByPacote(Pacote pacote);
    ImagemPacote findByPacoteAndDestaqueTrue(Pacote pacote);
}