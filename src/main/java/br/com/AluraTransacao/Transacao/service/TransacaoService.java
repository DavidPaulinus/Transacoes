package br.com.AluraTransacao.Transacao.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.AluraTransacao.Transacao.model.Transacao;
import br.com.AluraTransacao.Transacao.model.DTO.TransacaoDTO;

@Service
public class TransacaoService {
	private String path = "C:\\Users\\USER\\Downloads\\workspace\\eclipse\\transacoes-2022-01-01.csv";
	private List<Transacao> list = new ArrayList<>();;
	private BufferedReader br;
	
	public Page<Transacao> leituraArquivoCSV() throws IOException{
		br = new BufferedReader(new FileReader(path));
		
		String linha = br.readLine();
		while(linha != null) {
			String[] line = linha.split(",");
			list.add(new Transacao(new TransacaoDTO(line[0],line[1],line[2],line[3],line[4],line[5],new BigDecimal(line[6]), LocalDateTime.parse(line[7]))));
			
			linha = br.readLine();
		}
		
		br.close();
		return new PageImpl<Transacao>(list);
	}

}
