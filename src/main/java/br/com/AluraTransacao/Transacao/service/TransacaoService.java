package br.com.AluraTransacao.Transacao.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.AluraTransacao.Transacao.model.ListaImpotacoes;
import br.com.AluraTransacao.Transacao.model.Transacao;
import br.com.AluraTransacao.Transacao.model.DTO.importacoes.ImportacoesListarDTO;
import br.com.AluraTransacao.Transacao.model.DTO.transacao.TransacaoDTO;
import br.com.AluraTransacao.Transacao.util.Acao;
import br.com.AluraTransacao.Transacao.util.repository.ImportacoesRepository;
import br.com.AluraTransacao.Transacao.util.repository.TransacaoRepository;

@Service
public class TransacaoService {
	@Autowired
	private TransacaoRepository repo;
	@Autowired
	private ImportacoesRepository impoRepo;
	
	
	private String path = "C:\\Users\\USER\\Downloads\\workspace\\eclipse";
	private List<Transacao> list;
	private BufferedReader br;
	private Acao acao = new Acao();

	public Page<Transacao> leituraArquivoCSV(MultipartFile file) throws IOException {
		Path diretorioPath = Paths.get(path);
		Path arquivoPath = diretorioPath.resolve(file.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			file.transferTo(arquivoPath);
		}catch(IOException e){
			throw new RuntimeException("Erro ao salvar o arquivo");
		}
		
		list = new ArrayList<>();
		br = new BufferedReader(new FileReader(path+"\\"+file.getOriginalFilename()));
		
		String linha = br.readLine();
		while (linha != null) {
			String[] line = linha.split(",");
			list.add(new Transacao(new TransacaoDTO(line[0], line[1], line[2], line[3], line[4], line[5], Double.parseDouble(acao.ehBranco(line[6])), LocalDateTime.parse(line[7]))));

			linha = br.readLine();
		}

		br.close();
		return new PageImpl<Transacao>(list);
	}
	
	public List<ImportacoesListarDTO> listarTransacoes(){
		return impoRepo.findAll().stream().map(ImportacoesListarDTO::new).toList();
	}

	public List<Transacao> salvarDadosNoBD(MultipartFile file) throws IOException {
		var dados = leituraArquivoCSV(file);

		var primeiro = dados.getContent().get(0).getDataHoraTransacao();
		
		var listaBD = repo.findAll(); 
		
		var listaImpo = impoRepo.findAll();

		for (Transacao list : dados) {
			if (list.getDataHoraTransacao().getDayOfYear() == primeiro.getDayOfYear() && !acao.ehIgual(listaBD, list) && !acao.temBranco(list)) {
				repo.save(list);
				if(!acao.dataRepetida(list.getDataHoraTransacao().toLocalDate(), listaImpo)) {
					impoRepo.save(new ListaImpotacoes(LocalDateTime.now(), list.getDataHoraTransacao().toLocalDate()));
				}
			}
		}

		return repo.findAll();
	}

}
