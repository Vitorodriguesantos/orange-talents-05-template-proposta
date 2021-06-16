package br.com.zupacademy.templateproposta.viagens;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;

@Entity
public class Viagem {
	
	@Id 
	private String id = UUID.randomUUID().toString();
	@NotBlank
	private String destino;
	private LocalDateTime criadoEm = LocalDateTime.now();
	@NotNull
	private LocalDateTime terminoViagem;
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String agenteUser;
	@ManyToOne
	private Cartoes cartao;
	
	@Deprecated
	public Viagem() {
		
	}
	
	public Viagem(@NotBlank String destino, @NotBlank LocalDateTime terminoViagem, @NotBlank String ipCliente,
			@NotBlank String agenteUser, Cartoes cartao) {
		this.destino = destino;
		this.terminoViagem = terminoViagem;
		this.ipCliente = ipCliente;
		this.agenteUser = agenteUser;
		this.cartao = cartao;
	}
	
	
	
	
}
