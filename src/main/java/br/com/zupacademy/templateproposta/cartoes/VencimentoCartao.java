package br.com.zupacademy.templateproposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class VencimentoCartao {
	
	@Id
	private String id;
	@Positive
	private Integer dia;
	@NotNull
	private LocalDateTime dataDeCriacao;
	@ManyToOne
	private Cartoes cartao;
	
	@Deprecated
	public VencimentoCartao() {
		
	}

	public VencimentoCartao(String id, @Positive Integer dia, @NotNull LocalDateTime dataDeCriacao, Cartoes cartao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;
	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public Cartoes getCartao() {
		return cartao;
	}
	
	
}
