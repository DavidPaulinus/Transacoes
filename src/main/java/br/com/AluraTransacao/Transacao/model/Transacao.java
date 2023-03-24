package br.com.AluraTransacao.Transacao.model;

import java.time.LocalDateTime;

import br.com.AluraTransacao.Transacao.model.DTO.TransacaoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bancoOrigem;
	private String agenciaOrigem;
	private String contaOrigem;
	private String bancoDestino;
	private String agenciaDestino;
	private String contaDestino;
	private Double valor;
	private LocalDateTime dataHoraTransacao;

	public Transacao(TransacaoDTO dto) {
		if(dto == null) {
			throw new NullPointerException("Não é possível usar um arquivo em branco");
		}
		this.bancoOrigem = dto.bancoOrigem();
		this.agenciaDestino = dto.agenciaDestino();
		this.agenciaOrigem = dto.agenciaOrigem();
		this.bancoDestino = dto.bancoDestino();
		this.contaDestino = dto.contaDestino();
		this.contaOrigem = dto.contaOrigem();
		this.dataHoraTransacao = dto.dataHoraTransacao();
		this.valor = dto.valor();
	}
}
