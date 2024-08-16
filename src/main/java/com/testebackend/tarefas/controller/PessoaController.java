package com.testebackend.tarefas.controller;

import com.testebackend.tarefas.model.Pessoa;
import com.testebackend.tarefas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.adicionarPessoa(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
        pessoaService.removePessoa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Object[]>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/gastos")
    public ResponseEntity<Double> buscarPessoasPorNomeEPeriodo(@RequestParam String nome, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(pessoaService.buscarPessoasPorNomeEPeriodo(nome, startDate, endDate));
    }
}

