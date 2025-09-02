package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EnderecoTest {

        @Autowired
        private EnderecoRepository enderecoRepository;

        @Test
        public void inserirEndereco(){
            Endereco endereco = new Endereco();
            endereco.setCep("18136-090");
            endereco.setBairro("Vila Junqueira");
            endereco.setRua("Rua Castro Alves");
            endereco.setCidade("São Roque");
            endereco.setUf("SP");
            endereco.setNumero("12");
            endereco.setComplemento("Apartamento 2");

            enderecoRepository.save(endereco);
        }

        @Test
        public void alterarEndereco(){
            Endereco endereco = enderecoRepository.findById(1l).orElse(null);
            assertNotNull(endereco, "Endereço não encontrado");

            endereco.setComplemento("Apartamento 3");
            enderecoRepository.save(endereco);
        }

        @Test
        public void excluirEndereco(){
            Endereco endereco = enderecoRepository.findById(1l).orElse(null);
            assertNotNull(endereco, "Endereço não encontrado");

            enderecoRepository.delete(endereco);
        }


    @Test
    void getId() {
    }

    @Test
    void getCep() {
    }

    @Test
    void getRua() {
    }

    @Test
    void getComplemento() {
    }

    @Test
    void getBairro() {
    }

    @Test
    void getCidade() {
    }

    @Test
    void getNumero() {
    }

    @Test
    void getUf() {
    }

    @Test
    void setId() {
    }

    @Test
    void setCep() {
    }

    @Test
    void setRua() {
    }

    @Test
    void setComplemento() {
    }

    @Test
    void setBairro() {
    }

    @Test
    void setCidade() {
    }

    @Test
    void setNumero() {
    }

    @Test
    void setUf() {
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