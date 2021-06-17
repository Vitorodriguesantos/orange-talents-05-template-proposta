package br.com.zupacademy.templateproposta.viagens;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificaSistemaBancarioForm {
	
	@JsonProperty
	private String destino;
	@JsonProperty
	private String validoAte;
	
	public NotificaSistemaBancarioForm(String destino, String validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

}
