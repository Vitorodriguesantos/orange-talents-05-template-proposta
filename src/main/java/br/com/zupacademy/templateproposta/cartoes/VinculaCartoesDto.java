package br.com.zupacademy.templateproposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VinculaCartoesDto {
	
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	private VencimentoCartao vencimento;
	private String idProposta;
	
	@Deprecated
	public VinculaCartoesDto() {
		
	}
	
	public VinculaCartoesDto(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite,
			VencimentoCartao vencimento, String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.vencimento = vencimento;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public VencimentoCartao getVencimento() {
		return vencimento;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public Cartoes converter() {
		return new Cartoes(this.id, this.emitidoEm, this.titular, this.limite);
	}

	public VencimentoCartao vincularVencimento(Cartoes oCartao) {
		return new VencimentoCartao(this.vencimento.getId(), 
									this.vencimento.getDia(), 
									this.vencimento.getDataDeCriacao(), 
									oCartao);
	}
	
	
	
}
