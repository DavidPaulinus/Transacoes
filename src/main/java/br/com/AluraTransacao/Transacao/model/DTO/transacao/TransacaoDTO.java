package br.com.AluraTransacao.Transacao.model.DTO.transacao;

import java.time.LocalDateTime;

public record TransacaoDTO(
		 String bancoOrigem, 
		 String agenciaOrigem, 
		 String contaOrigem,
		 String bancoDestino, 
		 String agenciaDestino, 
		 String contaDestino, 
		 Double valor,
		 LocalDateTime dataHoraTransacao) {

}
