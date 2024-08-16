package com.testebackend.tarefas.controller;

import com.testebackend.tarefas.model.Tarefa;
import com.testebackend.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.adicionarTarefa(tarefa));
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<Tarefa> alocarPessoaNaTarefa(@PathVariable Long id, @RequestParam Long pessoaId) {
        return ResponseEntity.ok(tarefaService.alocarPessoaNaTarefa(id, pessoaId));
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.finalizarTarefa(id));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> listarTop3TarefasSemPessoasEPrazosMaisAntigos() {
        return ResponseEntity.ok(tarefaService.listarTop3TarefasSemPessoasEPrazosMaisAntigos());
    }
}

