package br.com.AluraTransacao.Transacao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_importacoes")
@Getter
@NoArgsConstructor
public class ListaImpotacoes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime importacao;
	private LocalDate transacao;

	public ListaImpotacoes(LocalDateTime importacao, LocalDate transacao) {
		super();
		this.importacao = importacao;
		this.transacao = transacao;
	}

}
