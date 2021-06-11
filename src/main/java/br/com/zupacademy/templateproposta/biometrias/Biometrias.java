package br.com.zupacademy.templateproposta.biometrias;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;

@Entity
public class Biometrias {
	
	@Id
	private String id;
	@NotNull
	private String biometriaB64;
	@ManyToOne
	private Cartoes cartao;
	
	public Biometrias(@NotNull String biometriaB64, Cartoes cartao) {
		super();
		this.biometriaB64 = biometriaB64;
		this.cartao = cartao;
	}
	
	@PrePersist
	private void gerarId() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
	

}
