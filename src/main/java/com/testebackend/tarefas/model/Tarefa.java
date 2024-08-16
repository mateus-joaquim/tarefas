package com.testebackend.tarefas.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate prazo;

    @Column(nullable = false, length = 100)
    private String departamento;

    @Column
    private Integer duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_alocada", nullable = true)
    private Pessoa pessoaAlocada;

    @Column(nullable = false)
    private boolean finalizado = false;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, LocalDate prazo, String departamento, Integer duracao, boolean finalizado, Pessoa pessoa) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.departamento = departamento;
        this.duracao = duracao;
        this.finalizado = finalizado;
        this.pessoaAlocada = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Pessoa getPessoaAlocada() {
        return pessoaAlocada;
    }

    public void setPessoaAlocada(Pessoa pessoaAlocada) {
        this.pessoaAlocada = pessoaAlocada;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
