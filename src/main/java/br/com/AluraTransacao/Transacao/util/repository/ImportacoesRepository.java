package br.com.AluraTransacao.Transacao.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AluraTransacao.Transacao.model.ListaImpotacoes;

public interface ImportacoesRepository extends JpaRepository<ListaImpotacoes, Long> {

}
