package br.com.zupacademy.templateproposta.cartoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Bloqueios {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String agenteUser;
	private LocalDateTime criadoEm = LocalDateTime.now();
	@ManyToOne
	private Cartoes cartao;
	
	@Deprecated
	public Bloqueios() {
		
	}
	
	public Bloqueios(@NotBlank String ipCliente, @NotBlank String agenteUser, Cartoes cartao) {
		super();
		this.ipCliente = ipCliente;
		this.agenteUser = agenteUser;
		this.cartao = cartao;
	}
	
}
