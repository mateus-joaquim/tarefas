package com.testebackend.tarefas.controllers;

import com.testebackend.tarefas.controller.DepartamentoController;
import com.testebackend.tarefas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartamentoControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private DepartamentoController departamentoController;

    @Test
    public void testeBuscarDepartamentoQtdPessoasETarefas() {
        Mockito.when(pessoaService.buscarDepartamentoQtdPessoasETarefas())
                .thenReturn(Arrays.asList(new Object[]{"TI", 5, 10}, new Object[]{"RH", 3, 5}));

        ResponseEntity<List<Object[]>> response = departamentoController.findDepartmentStats();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TI", Objects.requireNonNull(response.getBody()).get(0)[0]);
        assertEquals("RH", response.getBody().get(1)[0]);
    }
}

