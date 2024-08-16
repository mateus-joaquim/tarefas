package com.testebackend.tarefas.repository;

import com.testebackend.tarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p JOIN p.tarefas t WHERE p.nome = :nome AND t.prazo BETWEEN :startDate AND :endDate")
    List<Pessoa> buscarPorNomeEPeriodo(@Param("nome") String nome, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT p.departamento, COUNT(p), COUNT(t) FROM Pessoa p JOIN p.tarefas t GROUP BY p.departamento")
    List<Object[]> buscarDepartamentoQtdPessoasETarefas();

    @Query("SELECT p.nome, p.departamento, SUM(t.duracao) FROM Pessoa p JOIN p.tarefas t GROUP BY p.id")
    List<Object[]> listarPessoas();

    @Query("SELECT AVG(t.duracao) FROM Tarefa t WHERE t.pessoaAlocada.id = :pessoaId")
    double buscarPorNomeEPeriodoAMediaGastaPorTarefa(@Param("pessoaId") Long pessoaId);

}
