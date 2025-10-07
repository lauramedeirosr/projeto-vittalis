package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.NavioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NavioTest {

    @Autowired
    private NavioRepository navioRepository;

    @Test
    public void InserirNavio(){
        Navio navio = new Navio();
        navio.setNomeNavio("Navio 02");
        navio.setDescricao("Navio de passageiros famoso por seu naufrágio em 1912.");

        navioRepository.save(navio);
    }

    @Test
    public void AlterarNavio() {
        Navio navio = navioRepository.findById(1L).orElse(null);
        assertNotNull(navio, "Navio não encontrado");

        navio.setNomeNavio("Navio 01");

        navioRepository.save(navio);
    }

    @Test
    public void ExcluirNavio() {
        Navio navio = navioRepository.findById(1L).orElse(null);
        assertNotNull(navio, "Navio não encontrado");

        navioRepository.delete(navio);
    }


    @Test
    void getId() {
    }

    @Test
    void getNomeNavio() {
    }

    @Test
    void getDescricao() {
    }

    @Test
    void getQuarto1() {
    }

    @Test
    void getQuarto2() {
    }

    @Test
    void getQuarto3() {
    }

    @Test
    void setId() {
    }

    @Test
    void setNomeNavio() {
    }

    @Test
    void setDescricao() {
    }

    @Test
    void setQuarto1() {
    }

    @Test
    void setQuarto2() {
    }

    @Test
    void setQuarto3() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}