package br.com.vittalis.sistema.repository;

import br.com.vittalis.sistema.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository  extends  JpaRepository<Telefone, Long> {
    Telefone findTelefoneByDddAndNumero(String ddd, String numero);
}
