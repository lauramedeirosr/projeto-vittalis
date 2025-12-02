package br.com.vittalis.sistema.api;

import br.com.vittalis.sistema.model.Quarto;
import br.com.vittalis.sistema.repository.NavioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/navios")
public class NavioRestController {

    @Autowired
    private NavioRepository navioRepository;

    @GetMapping("/{id}/vagas")
    public Long getVagasPorNavio(@PathVariable("id") Long id) {
        return navioRepository.findById(id)
                .map(navio -> navio.getQuartos().stream()
                        .map(Quarto::getQuantidadeDisponivel)
                        .filter(qtd -> qtd != null)
                        .mapToLong(Long::longValue)
                        .sum())
                .orElse(0L);
    }
}