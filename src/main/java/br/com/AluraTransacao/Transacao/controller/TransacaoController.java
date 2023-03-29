package br.com.AluraTransacao.Transacao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.AluraTransacao.Transacao.service.TransacaoService;
import jakarta.transaction.Transactional;

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
	@Transactional
	public String salvarDadosNoBD2(@RequestParam MultipartFile file, Model mod) throws IOException{
		var dados = service.salvarDadosNoBD(file);
		mod.addAttribute("Transacoes",dados);
		return "listaTransacoes";
	}
}
