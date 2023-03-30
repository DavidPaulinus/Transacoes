package br.com.AluraTransacao.Transacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.AluraTransacao.Transacao.infra.TokenService;
import br.com.AluraTransacao.Transacao.model.Usuario;
import br.com.AluraTransacao.Transacao.model.DTO.autenticacao.DadosAutenticacaoDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService tolkien;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dto){
		var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
		var authentication = manager.authenticate(token);
		
		return ResponseEntity.ok(tolkien.gerarToken((Usuario)authentication.getPrincipal()));
	}
}
