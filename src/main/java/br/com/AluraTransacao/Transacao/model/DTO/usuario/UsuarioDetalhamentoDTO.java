package br.com.AluraTransacao.Transacao.model.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDetalhamentoDTO(
		@NotNull
		Long id,
		
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email) {

}
