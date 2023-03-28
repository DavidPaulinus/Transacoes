package br.com.AluraTransacao.Transacao.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.AluraTransacao.Transacao.model.ListaImpotacoes;
import br.com.AluraTransacao.Transacao.model.Transacao;

@Component
public class Acao {

	public boolean ehIgual(List<Transacao> dados, Transacao list) {
		for (Transacao y : dados) {
			if (y.getAgenciaDestino().equals(list.getAgenciaDestino())
					&& y.getAgenciaOrigem().equals(list.getAgenciaOrigem())
					&& y.getBancoDestino().equals(list.getBancoDestino())
					&& y.getBancoOrigem().equals(list.getBancoOrigem())
					&& y.getContaDestino().equals(list.getContaDestino())
					&& y.getContaOrigem().equals(list.getContaOrigem())
					&& y.getDataHoraTransacao().equals(list.getDataHoraTransacao())
					&& y.getValor().equals(list.getValor())) {

				return true;
			}

		}
		return false;
	}

	public boolean temBranco(Transacao list) {
		if (list.getAgenciaDestino().isBlank() 
				|| list.getAgenciaOrigem().isBlank() 
				|| list.getBancoDestino().isBlank()
				|| list.getBancoOrigem().isBlank() 
				|| list.getContaDestino().isBlank()
				|| list.getContaOrigem().isBlank() 
				|| list.getDataHoraTransacao() == null 
				|| list.getValor() == -10) {
			return true;
		}

		return false;
	}

	public String ehBranco(String string) {
		if (string.isEmpty()) {
			return "-10";
		}
		return string;
	}

	public boolean dataRepetida(LocalDate localDate, List<ListaImpotacoes> listaImpo) {
		for(ListaImpotacoes lis:listaImpo) {
			if (localDate.getDayOfYear() == lis.getTransacao().getDayOfYear()) {
				return true;
			}	
		}

		return false;
	}

}
