package br.com.zupacademy.templateproposta.carteiras;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;

@Entity
public class CarteiraDigital {
	
	@Id
	private String id = UUID.randomUUID().toString();
	@Email
	private String email;
	@Enumerated(EnumType.STRING)
	private NomeCarteira nome;
	@ManyToOne
	private Cartoes cartao;
	
	@Deprecated
	public CarteiraDigital() {
		
	}
	
	public CarteiraDigital(@Email String email, NomeCarteira nome, Cartoes cartao) {
		this.email = email;
		this.nome = nome;
		this.cartao = cartao;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public NomeCarteira getNome() {
		return nome;
	}

}
