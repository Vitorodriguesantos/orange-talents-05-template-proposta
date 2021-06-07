package br.com.zupacademy.templateproposta.propostas;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.templateproposta.validacao.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CPFOrCNPJ
	private String CPFouCNPJ;
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@Positive
	private BigDecimal salario;
	@Enumerated(EnumType.STRING)
	private StatusProposta status;
	
	public Proposta() {
		
	}
	
	public Proposta(String cPFouCNPJ, @Email String email, @NotBlank String nome, @NotBlank String endereco,
			@Positive BigDecimal salario) {
		super();
		this.CPFouCNPJ = cPFouCNPJ;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getCPFouCNPJ() {
		return CPFouCNPJ;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setStatus(StatusProposta status) {
        this.status = status;
    }

}
