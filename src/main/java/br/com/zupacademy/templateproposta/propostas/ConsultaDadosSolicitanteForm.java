package br.com.zupacademy.templateproposta.propostas;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonComponent
public class ConsultaDadosSolicitanteForm {

	@JsonProperty
    private String documento;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String idProposta;
    
    @Deprecated
    public ConsultaDadosSolicitanteForm() {
    	
    }
    
	public ConsultaDadosSolicitanteForm(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}
    
}
