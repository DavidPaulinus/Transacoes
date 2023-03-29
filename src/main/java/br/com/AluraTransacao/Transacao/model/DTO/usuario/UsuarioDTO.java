package br.com.AluraTransacao.Transacao.model.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email) {

}
