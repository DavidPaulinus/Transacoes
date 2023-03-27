package br.com.AluraTransacao.Transacao.model.DTO.importacoes;

import java.time.format.DateTimeFormatter;

import br.com.AluraTransacao.Transacao.model.ListaImpotacoes;

public record ImportacoesListarDTO(String transacao, String importacoes) {
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ImportacoesListarDTO(ListaImpotacoes a) {
		this(df.format(a.getTransacao()), dtf.format(a.getImportacao()));
	}
}
