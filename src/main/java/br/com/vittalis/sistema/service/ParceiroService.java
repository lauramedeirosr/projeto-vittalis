package br.com.vittalis.sistema.service;

import br.com.vittalis.sistema.model.Parceiro;
import br.com.vittalis.sistema.repository.ParceiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;

    public List<Parceiro> listarParceiros() {
        return parceiroRepository.findAll();
    }

    public Parceiro salvar(Parceiro parceiro) {
        return parceiroRepository.save(parceiro);
    }

    public Optional<Parceiro> buscarPorId(Long id) {
        return parceiroRepository.findById(id);
    }

    public void excluir(Long id) {
        parceiroRepository.deleteById(id);
    }

    public List<Parceiro> buscarPorNome(String nome) {
        return parceiroRepository.findByNomeEmpresaContainingIgnoreCase(nome);
    }
}
