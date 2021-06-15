package br.com.zupacademy.templateproposta.cartoes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificarBloqueioForm {
	
	@JsonProperty
	private String sistemaResponsavel;
	
	public NotificarBloqueioForm(String sistema) {
		this.sistemaResponsavel = sistema;
	}

}
