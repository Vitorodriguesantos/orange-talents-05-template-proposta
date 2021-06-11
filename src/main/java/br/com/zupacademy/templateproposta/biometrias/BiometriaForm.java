package br.com.zupacademy.templateproposta.biometrias;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.validacao.Base64;

public class BiometriaForm {
	
	@NotNull @Base64
	private String biometriaB64;
	
	@Deprecated
	public BiometriaForm() {
		
	}
	
	public BiometriaForm(@NotNull String biometriaB64) {
		this.biometriaB64 = biometriaB64;
	}

	public String getBiometriaB64() {
		return biometriaB64;
	}

	public Biometrias novaBiometria(Cartoes cartoes) {
		return new Biometrias(biometriaB64, cartoes);
	}

}
