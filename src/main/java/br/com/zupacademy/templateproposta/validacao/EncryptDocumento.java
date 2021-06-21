package br.com.zupacademy.templateproposta.validacao;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public abstract class EncryptDocumento {
	
	private final static int valor = 12;
	
	public static String encriptDocumento(String documento) {
		TextEncryptor segredo = Encryptors.queryableText("documento", "5c0744940b5c369b");
		return segredo.encrypt(documento);
	}

}
