package br.com.vittalis.sistema.model;

import br.com.vittalis.sistema.repository.AdministradorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdministradorTest extends Pessoa {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Test
    public void InserirAdministrador() {
        Administrador administrador = new Administrador();
        administrador.setNomeCompleto("João da Silva");
        administrador.setCpf("47782505089");
        administrador.setEmail("joão.silva@gmail.com");
        administrador.setDataNascimento(Date.valueOf("2025-01-01"));
        administrador.setTelefone("11987654321");
        administrador.setSenha("senha123");

        administradorRepository.save(administrador);
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