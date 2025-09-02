package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.QuartoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuartoTest {

    @Autowired
    private QuartoRepository quartoRepository;

    @Test
    public void InserirQuarto(){
        Quarto quarto = new Quarto();
        quarto.setNome("Quarto VIP");
        quarto.setDescricao("Quarto com vista para o mar e serviço de quarto 24 horas.");
        quarto.setQuantidadeDisponivel(5);
    }

    @Test
    public void AlterarQuarto(){
        Quarto quarto = quartoRepository.findById(1l).orElse(null);
        assertNotNull(quarto, "Quarto não encontrado");

        quarto.setDescricao("Quarto com vista para o mar, serviço de quarto 24 horas e banheira de hidromassagem.");
        quartoRepository.save(quarto);
    }

    @Test
    public void ExcluirQuarto(){
        Quarto quarto = quartoRepository.findById(1l).orElse(null);
        assertNotNull(quarto, "Quarto não encontrado");

        quartoRepository.delete(quarto);
    }


    @Test
    void getId() {
    }

    @Test
    void getNome() {
    }

    @Test
    void getDescricao() {
    }

    @Test
    void getQuantidadeDisponivel() {
    }

    @Test
    void setId() {
    }

    @Test
    void setNome() {
    }

    @Test
    void setDescricao() {
    }

    @Test
    void setQuantidadeDisponivel() {
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