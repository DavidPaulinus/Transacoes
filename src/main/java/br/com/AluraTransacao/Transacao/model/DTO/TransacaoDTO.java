package br.com.AluraTransacao.Transacao.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransacaoDTO(
		@NotBlank String bancoOrigem, 
		@NotBlank String agenciaOrigem, 
		@NotBlank String contaOrigem,
		@NotBlank String bancoDestino, 
		@NotBlank String agenciaDestino, 
		@NotBlank String contaDestino, 
		@NotNull BigDecimal valor, 
		@NotNull 
		LocalDateTime dataHoraTransacao) {

}
