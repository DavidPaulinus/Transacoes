package br.com.AluraTransacao.Transacao.model.DTO;

import java.time.LocalDateTime;

import br.com.AluraTransacao.Transacao.model.Transacao;

public record ArquivoListarDTO(String bancoOrigem, String agenciaOrigem, String contaOrigem,
		String bancoDestino, String agenciaDestino, String contaDestino, Double valor,LocalDateTime dataHoraTransacao) {
	public ArquivoListarDTO(Transacao tr) {
		this(tr.getBancoOrigem(), tr.getAgenciaOrigem(), tr.getContaOrigem(), tr.getBancoDestino(), tr.getAgenciaDestino(), tr.getContaDestino(), tr.getValor(), tr.getDataHoraTransacao());
	}
}
