package br.com.AluraTransacao.Transacao.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.AluraTransacao.Transacao.model.Transacao;
import br.com.AluraTransacao.Transacao.model.DTO.TransacaoDTO;
import br.com.AluraTransacao.Transacao.util.Acao;
import br.com.AluraTransacao.Transacao.util.repository.TransacaoRepository;

@Service
public class TransacaoService {
	@Autowired
	private TransacaoRepository repo;

	private String path = "C:\\Users\\USER\\Downloads\\workspace\\eclipse\\exemplo.csv";
	private List<Transacao> list;
	private BufferedReader br;
	private Acao acao = new Acao();

	public Page<Transacao> leituraArquivoCSV() throws IOException {
		list = new ArrayList<>();
		br = new BufferedReader(new FileReader(path));

		String linha = br.readLine();
		while (linha != null) {
			String[] line = linha.split(",");
			list.add(new Transacao(new TransacaoDTO(line[0], line[1], line[2], line[3], line[4], line[5], Double.parseDouble(acao.ehBranco(line[6])), LocalDateTime.parse(line[7]))));

			linha = br.readLine();
		}

		br.close();
		return new PageImpl<Transacao>(list);
	}

	public Page<Transacao> salvarDadosNoBD() throws IOException {
		var dados = leituraArquivoCSV();

		var primeiro = dados.getContent().get(0).getDataHoraTransacao();
		
		var listaBD = repo.findAll(); 

		for (Transacao list : dados) {
			if (list.getDataHoraTransacao().getDayOfYear() == primeiro.getDayOfYear() && acao.ehIgual(listaBD, list) == false && acao.temBranco(list) == false) {
				repo.save(list);
			}
		}

		return new PageImpl<Transacao>(repo.findAll());
	}

}
