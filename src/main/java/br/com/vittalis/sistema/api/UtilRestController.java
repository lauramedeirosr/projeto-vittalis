package br.com.vittalis.sistema.api;

import br.com.vittalis.sistema.repository.NavioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/util")
public class UtilRestController {

    @Autowired
    NavioRepository navioRepository;

    @GetMapping("/navios/qtdQuartos" )
    public Long getQtdQuartosNavios() {
        return navioRepository.countByQuartosIsNotNull();
    }
}
