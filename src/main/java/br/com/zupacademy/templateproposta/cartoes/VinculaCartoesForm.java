package br.com.zupacademy.templateproposta.cartoes;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonComponent
public class VinculaCartoesForm {
	
	@JsonProperty
    private String documento;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String idProposta;
    
    @Deprecated
    public VinculaCartoesForm() {
    	
    }

	public VinculaCartoesForm(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

}
