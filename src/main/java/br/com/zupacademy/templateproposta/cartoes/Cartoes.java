package br.com.zupacademy.templateproposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
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
	
}
