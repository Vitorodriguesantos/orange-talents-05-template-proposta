package br.com.zupacademy.templateproposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartoes {
	
	@Id
	private String id;
	@NotNull
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;
	@NotNull
	private BigDecimal limite;
	@Enumerated(EnumType.STRING)
	private StatusCartao statusCartao = StatusCartao.DESBLOQUEADO;
	
	@Deprecated
	public Cartoes() {
		
	}

	public Cartoes(String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, @NotNull BigDecimal limite) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
	}

	public String getId() {
		return id;
	}
	
	public void setStatusCartao(StatusCartao status) {
		this.statusCartao = status;
	}

	public boolean estaBloqueado() {
		return this.statusCartao.equals(StatusCartao.BLOQUEADO);
	}
	
}
