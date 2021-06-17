package br.com.zupacademy.templateproposta.carteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.validacao.ValidaEnum;

public class CarteiraDigitalForm {
	
    @JsonProperty
    @NotBlank @Email
    public String email;
    @JsonProperty
    @NotBlank @ValidaEnum(enumClass = NomeCarteira.class)
    public String nome;
    
	public CarteiraDigitalForm(@NotBlank @Email String email, @NotBlank String nome) {
		this.email = email;
		this.nome = nome;
	}

	public CarteiraDigital converter(Cartoes cartao) {
		
		return new CarteiraDigital(email, NomeCarteira.valueOf(nome), cartao);
	}
    
}
