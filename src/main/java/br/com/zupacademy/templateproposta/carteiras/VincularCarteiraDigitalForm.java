package br.com.zupacademy.templateproposta.carteiras;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VincularCarteiraDigitalForm {
	
	@JsonProperty
	private String email;
	@JsonProperty
	private String carteira;
	
	public VincularCarteiraDigitalForm(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

}
