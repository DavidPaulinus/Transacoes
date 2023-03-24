package br.com.AluraTransacao.Transacao.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AluraTransacao.Transacao.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
