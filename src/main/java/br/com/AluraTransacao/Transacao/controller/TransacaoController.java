package br.com.AluraTransacao.Transacao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.AluraTransacao.Transacao.model.DTO.ArquivoListarDTO;
import br.com.AluraTransacao.Transacao.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	@Autowired
	private TransacaoService service;
	
	@GetMapping
	public ResponseEntity<Page<ArquivoListarDTO>> leituraArquivoCSV() throws IOException{
		return ResponseEntity.ok(service.leituraArquivoCSV().map(ArquivoListarDTO::new));
	}
	
	@PostMapping
	public ResponseEntity<Page<ArquivoListarDTO>> salvarDadosNoBD() throws IOException {
		return ResponseEntity.ok(service.salvarDadosNoBD().map(ArquivoListarDTO::new));
	}
}
