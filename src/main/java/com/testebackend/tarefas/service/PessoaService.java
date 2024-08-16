package com.testebackend.tarefas.service;

import com.testebackend.tarefas.model.Pessoa;
import com.testebackend.tarefas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setDepartamento(pessoaDetails.getDepartamento());
        return pessoaRepository.save(pessoa);
    }

    public void removePessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoaRepository.delete(pessoa);
    }

    public List<Object[]> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    public Double buscarPessoasPorNomeEPeriodo(String nome, LocalDate startDate, LocalDate endDate) {
        List<Pessoa> pessoas = pessoaRepository.buscarPorNomeEPeriodo(nome, startDate, endDate);
        if (pessoas.isEmpty()) {
            throw new RuntimeException("Nenhuma pessoa encontrada");
        }
        return pessoas.stream().mapToDouble(p -> pessoaRepository.buscarPorNomeEPeriodoAMediaGastaPorTarefa(p.getId())).average().orElse(0.0);
    }

    public List<Object[]> buscarDepartamentoQtdPessoasETarefas() {
        return pessoaRepository.buscarDepartamentoQtdPessoasETarefas();
    }
}

