package br.com.AluraTransacao.Transacao.model;

import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDTO;
import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDetalhamentoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_Usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String nome;
	private String senha;
	private Boolean ativo;

	public Usuario(@Valid UsuarioDTO dto, String senha) {
		this.nome = dto.nome();
		this.email = dto.email();
		this.senha = senha;
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

	public void atualizar(@Valid UsuarioDetalhamentoDTO dto) {
		this.nome = dto.nome();
		this.email = dto.email();
	}
}
