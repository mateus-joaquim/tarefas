package com.testebackend.tarefas.controllers;

import com.testebackend.tarefas.controller.PessoaController;
import com.testebackend.tarefas.model.Pessoa;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @Test
    public void testeAdicionarPessoa() {
        Pessoa pessoa = new Pessoa(1L, "João", "TI");

        Mockito.when(pessoaService.adicionarPessoa(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.adicionarPessoa(pessoa);

        assertEquals(200, response.getStatusCode());
        assertEquals("João", response.getBody().getNome());
    }

    @Test
    public void testeAtualizarPessoa() {
        Pessoa pessoa = new Pessoa(1L, "João", "TI");

        Mockito.when(pessoaService.atualizarPessoa(eq(1L), any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.atualizarPessoa(1L, pessoa);

        assertEquals(200, response.getStatusCode());
        assertEquals("João", response.getBody().getNome());
    }

    @Test
    public void testeRemoverPessoa() {
        Mockito.doNothing().when(pessoaService).removePessoa(1L);

        ResponseEntity<Void> response = pessoaController.removerPessoa(1L);

        assertEquals(204, response.getStatusCode());
    }

    @Test
    public void testeListarPessoas() {
        Mockito.when(pessoaService.listarPessoas())
                .thenReturn(Arrays.asList(new Object[]{"João", "TI", 40}, new Object[]{"Maria", "RH", 20}));

        ResponseEntity<List<Object[]>> response = pessoaController.listarPessoas();

        assertEquals(200, response.getStatusCode());
        assertEquals("João", response.getBody().get(0)[0]);
        assertEquals("Maria", response.getBody().get(1)[0]);
    }

    @Test
    public void testeBuscarPessoasPorNomeEPeriodo() {
        Mockito.when(pessoaService.buscarPessoasPorNomeEPeriodo("João", null, null))
                .thenReturn(8.0);

        ResponseEntity<Double> response = pessoaController.buscarPessoasPorNomeEPeriodo("João", null, null);

        assertEquals(200, response.getStatusCode());
        assertEquals(8.0, response.getBody());
    }
}

