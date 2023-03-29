package br.com.AluraTransacao.Transacao.service;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.AluraTransacao.Transacao.model.Usuario;
import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDTO;
import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDetalhamentoDTO;
import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioListarDTO;
import br.com.AluraTransacao.Transacao.util.Gerador;
import br.com.AluraTransacao.Transacao.util.MailServerProperties;
import br.com.AluraTransacao.Transacao.util.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private Gerador geraldo;
	private JavaMailSender mailSender = new MailServerProperties().getJavaMailSender();
		

	public List<Usuario> cadastrar(@Valid UsuarioDTO dto) throws AuthenticationException {
		if(emailExiste(dto.email(), repo.findAll())) {
			throw new AuthenticationException("E-mail já usado em outra conta.");
		}
		
		var senha = geraldo.gerar();
		repo.save(new Usuario(dto, geraldo.codificar(senha)));
		
		enviarSenhaViaEmail(dto.email(), senha.toString());
		
		return repo.findAll().stream().filter(x -> x.getAtivo() == true).toList();
	}
	
	private void enviarSenhaViaEmail(String senha, String email) {
		 SimpleMailMessage message = new SimpleMailMessage();
		 
	     message.setText("Ola!\n Segue em anexo a sua senha para acessar o SISTEMA DE ANÁLISE DE TRANSAÇÕES FIANCEIRAS.\n Senha: " + senha);
	     message.setTo(email);
	     message.setFrom(".com");
	     
	     try {
	         mailSender.send(message);
	     } catch (Exception e) {
	        
	     }
	}
	
	private Boolean emailExiste(String dto, List<Usuario> list) {
		for(Usuario email:list) {
			if(email.getEmail().equals(dto) && email.getAtivo()) {
				return true;
			}
		}
		return false;
	}

	public void excluir(Long id) {
		var conta = repo.getReferenceById(id);
		conta.excluir();
	}

	public Usuario pegarPorId(Long id) {
		return repo.getReferenceById(id);
	}

	public List<UsuarioListarDTO> detalharUsuarios() {
		return repo.findAll().stream().filter(x -> x.getAtivo() == true).map(UsuarioListarDTO::new).toList();
	}

	public void atualizar(@Valid UsuarioDetalhamentoDTO dto) {
		repo.getReferenceById(dto.id()).atualizar(dto);
	}

}
