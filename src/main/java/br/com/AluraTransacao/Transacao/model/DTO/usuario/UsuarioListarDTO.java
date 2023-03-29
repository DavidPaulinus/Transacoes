package br.com.AluraTransacao.Transacao.model.DTO.usuario;

import br.com.AluraTransacao.Transacao.model.Usuario;

public record UsuarioListarDTO(Long id, String nome, String email) {
	public UsuarioListarDTO(Usuario us) {
		this(us.getId(), us.getNome(), us.getEmail());
	}
}
