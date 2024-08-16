package com.testebackend.tarefas.controllers;

import com.testebackend.tarefas.controller.TarefaController;
import com.testebackend.tarefas.model.Tarefa;
import com.testebackend.tarefas.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    @Test
    public void testeAdicionarTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", LocalDate.now(), "TI", 5, false, null);

        Mockito.when(tarefaService.adicionarTarefa(any(Tarefa.class))).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.addTarefa(tarefa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tarefa 1", response.getBody().getTitulo());
    }

    @Test
    public void testeAlocarPessoaNaTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", LocalDate.now(), "TI", 5, false, null);

        Mockito.when(tarefaService.alocarPessoaNaTarefa(eq(1L), eq(1L))).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.alocarPessoaNaTarefa(1L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tarefa 1", response.getBody().getTitulo());
    }

    @Test
    public void testeFinalizarTarefa() {
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1", "Descrição 1", LocalDate.now(), "TI", 5, true, null);

        Mockito.when(tarefaService.finalizarTarefa(1L)).thenReturn(tarefa);

        ResponseEntity<Tarefa> response = tarefaController.finalizarTarefa(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(Objects.requireNonNull(response.getBody()).getFinalizado());
    }

    @Test
    public void testeListarTop3TarefasSemPessoasEPrazosMaisAntigos() {
        Tarefa tarefa1 = new Tarefa(1L, "Tarefa 1", "Descrição 1", LocalDate.now().minusDays(10), "TI", 30, false, null);
        Tarefa tarefa2 = new Tarefa(2L, "Tarefa 2", "Descrição 2", LocalDate.now().minusDays(5), "TI", 30, false, null);
        Tarefa tarefa3 = new Tarefa(3L, "Tarefa 3", "Descrição 3", LocalDate.now().minusDays(2), "TI", 30, false, null);

        Mockito.when(tarefaService.listarTop3TarefasSemPessoasEPrazosMaisAntigos()).thenReturn(Arrays.asList(tarefa1, tarefa2, tarefa3));

        ResponseEntity<List<Tarefa>> response = tarefaController.listarTop3TarefasSemPessoasEPrazosMaisAntigos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tarefa 1", response.getBody().get(0).getTitulo());
        assertEquals("Tarefa 2", response.getBody().get(1).getTitulo());
    }
}

