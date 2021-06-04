package br.com.zupacademy.templateproposta.propostas;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.templateproposta.validacao.CPFOrCNPJ;

public class PropostaForm {

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
	
	public PropostaForm(String cPFouCNPJ, @Email String email, @NotBlank String nome, @NotBlank String endereco,
			@Positive BigDecimal salario) {
		super();
		this.CPFouCNPJ = cPFouCNPJ;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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
	
	public Proposta converter() {
		return new Proposta(CPFouCNPJ, email, nome, endereco, salario);
	}
	
	
}
