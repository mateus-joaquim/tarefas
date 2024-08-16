package com.testebackend.tarefas.service;

import com.testebackend.tarefas.model.Pessoa;
import com.testebackend.tarefas.model.Tarefa;
import com.testebackend.tarefas.repository.PessoaRepository;
import com.testebackend.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa alocarPessoaNaTarefa(Long tarefaId, Long pessoaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!pessoa.getDepartamento().equals(tarefa.getDepartamento())) {
            throw new RuntimeException("Departamento diferente, alocação não permitida");
        }

        tarefa.setPessoaAlocada(pessoa);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa finalizarTarefa(Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setFinalizado(true);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTop3TarefasSemPessoasEPrazosMaisAntigos() {
        return tarefaRepository.listarTop3TarefasSemPessoasEPrazosMaisAntigos();
    }
}

