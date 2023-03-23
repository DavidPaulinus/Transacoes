package br.com.AluraTransacao.Transacao.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.AluraTransacao.Transacao.model.Transacao;
import jakarta.validation.constraints.NotBlank;

public record ArquivoListarDTO(@NotBlank String bancoOrigem, String agenciaOrigem, String contaOrigem,
		String bancoDestino, String agenciaDestino, String contaDestino, BigDecimal valor,LocalDateTime dataHoraTransacao) {
	public ArquivoListarDTO(Transacao tr) {
		this(tr.getBancoOrigem(), tr.getAgenciaOrigem(), tr.getContaOrigem(), tr.getBancoDestino(), tr.getAgenciaDestino(), tr.getContaDestino(), tr.getValor(), tr.getDataHoraTransacao());
	}
}
