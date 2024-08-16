package com.testebackend.tarefas.controller;

import com.testebackend.tarefas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Object[]>> findDepartmentStats() {
        return ResponseEntity.ok(pessoaService.buscarDepartamentoQtdPessoasETarefas());
    }
}

