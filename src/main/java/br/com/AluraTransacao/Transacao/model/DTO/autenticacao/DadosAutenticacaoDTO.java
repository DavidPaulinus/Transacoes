package br.com.AluraTransacao.Transacao.model.DTO.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(
		@NotBlank
		String login, 
		
		@NotBlank
		String senha) {

}
