package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClienteTest extends Pessoa {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void cadastrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto("João da Silva");
        cliente.setCpf("47782505089");
        cliente.setEmail("joão.silva@gmail.com");
        cliente.setDataNascimento(Date.valueOf("2025-01-01"));
        cliente.setTelefone("11987654321");
        clienteRepository.save(cliente);
    }

    @Test
    public void alterarCliente() {
        Cliente cliente = clienteRepository.findById(26l).orElse(null);
        assertNotNull(cliente, "Cliente não encontrado");

        cliente.setNomeCompleto("João Pereira");
        clienteRepository.save(cliente);
    }

    @Test
    public void excluirCliente() {
        Cliente cliente = clienteRepository.findById(26l).orElse(null);
        assertNotNull(cliente, "Cliente não encontrado");

        clienteRepository.delete(cliente);
    }

    @Test
    void testGetId() {
    }

    @Test
    void testGetNomeCompleto() {
    }

    @Test
    void testGetCpf() {
    }

    @Test
    void testGetEmail() {
    }

    @Test
    void testGetDataNascimento() {
    }

    @Test
    void testGetDataCadastro() {
    }

    @Test
    void testGetEndereco() {
    }

    @Test
    void testGetTelefone() {
    }

    @Test
    void testGetSenha() {
    }

    @Test
    void testGetUser() {
    }

    @Test
    void testSetId() {
    }

    @Test
    void testSetNomeCompleto() {
    }

    @Test
    void testSetCpf() {
    }

    @Test
    void testSetEmail() {
    }

    @Test
    void testSetDataNascimento() {
    }

    @Test
    void testSetDataCadastro() {
    }

    @Test
    void testSetEndereco() {
    }

    @Test
    void testSetTelefone() {
    }

    @Test
    void testSetSenha() {
    }

    @Test
    void testSetUser() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testCanEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}