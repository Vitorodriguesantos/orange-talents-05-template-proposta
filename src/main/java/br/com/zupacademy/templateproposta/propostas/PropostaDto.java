package br.com.zupacademy.templateproposta.propostas;

import java.math.BigDecimal;

public class PropostaDto {
	
	private String CPFouCNPJ;
	private String email;
	private String endereco;
	private String nome;
	private BigDecimal salario;
	private String status;
	private String idCartao;
	
	public String getCPFouCNPJ() {
		return CPFouCNPJ;
	}
	public String getEmail() {
		return email;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public String getStatus() {
		return status;
	}
	public String getIdCartao() {
		return idCartao;
	}
	
	public PropostaDto(Proposta proposta) {
		this.CPFouCNPJ = proposta.getCPFouCNPJ();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.idCartao = proposta.getCartao().getId();
		this.nome = proposta.getNome();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus().toString();
	}

}
