package br.com.AluraTransacao.Transacao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.AluraTransacao.Transacao.service.TransacaoService;

@Controller
@RequestMapping("/transacao")
public class TransacaoController {
	@Autowired
	private TransacaoService service;
	
	@GetMapping
	public String leituraArquivoCSV(Model mod) throws IOException{
		mod.addAttribute("Transacoes",service.listarTransacoes());
		return "home";
	}
	
	@PostMapping
	public String salvarDadosNoBD(Model mod) throws IOException{
		mod.addAttribute("Transacoes", service.salvarDadosNoBD());
		return "listaTransacoes";
	}
}
