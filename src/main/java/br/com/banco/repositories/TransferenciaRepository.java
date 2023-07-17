package br.com.banco.repositories;

import br.com.banco.models.Transferencia;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;



public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @EntityGraph(value = "transferencia.conta", type = EntityGraphType.FETCH, attributePaths = {"conta"})
    List<Transferencia> findByContaId(@Param("id") Long id);
    @EntityGraph(value = "transferencia.data_transferencia", type = EntityGraphType.FETCH, attributePaths = {"dataTransferencia"})
    List<Transferencia> findByDataTransferenciaBetween(@Param("data_inicial") Timestamp data_inicial, @Param("data_final") Timestamp data_final);
    @EntityGraph(value = "transferencia.nome_operador_transacao", type = EntityGraphType.FETCH, attributePaths = {"nomeOperadorTransacao"})
    List<Transferencia> findByNomeOperadorTransacaoIgnoreCase(String nome_operador_transacao);
    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = {"nomeOperadorTransacao", "dataTransferencia"})
    List<Transferencia> findBynomeOperadorTransacaoIgnoreCaseAndDataTransferenciaBetween(String nome_operador_transacao, Timestamp data_inicial, Timestamp data_final);
}
