package br.com.AluraTransacao.Transacao.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDTO;
import br.com.AluraTransacao.Transacao.model.DTO.usuario.UsuarioDetalhamentoDTO;
import br.com.AluraTransacao.Transacao.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/lista")
	public String listaUsuarios(Model mod) {
		mod.addAttribute("usuarios", service.detalharUsuarios());
		return "listaUsuarios";
	}
	
	@GetMapping
	public String login() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String cadastroUserForm() {
		return "cadastraForm";
	}
	
	@PostMapping
	@Transactional
	public String cadastroUserBD(@Valid UsuarioDTO dto) throws AuthenticationException {
		service.cadastrar(dto);
		return "redirect:/user/lista";
	}
	
	@PostMapping("/salvo")
	@Transactional
	public String atualizarUser(@Valid UsuarioDetalhamentoDTO dto) {
		service.atualizar(dto);
		return "redirect:/user/lista";
	}
	
	@GetMapping("/upt")
	@Transactional
	public String editaConta(@RequestParam("id") Long id, Model mod) {
		mod.addAttribute("usuario", service.pegarPorId(id));
		return "editarUsuario";
	}
	
	@GetMapping("/del")
	@Transactional
	public String deletarConta(@RequestParam("id") Long id) {
		service.excluir(id);
		return "redirect:lista";
	}
}
