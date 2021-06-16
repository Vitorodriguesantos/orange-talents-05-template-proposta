package br.com.zupacademy.templateproposta.viagens;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;

public class ViagemForm {
	
	@NotBlank
	private String destino;
	@FutureOrPresent
	@NotNull
	private LocalDateTime terminoViagem;
	
	@Deprecated
	public ViagemForm() {
		
	}
	
	public ViagemForm(@NotBlank String destino, @NotNull LocalDateTime terminoViagem) {
		this.destino = destino;
		this.terminoViagem = terminoViagem;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getTerminoViagem() {
		return terminoViagem;
	}

	public Viagem converter(Cartoes cartoes, String ipCliente, String agenteUser) {

		return new Viagem(destino, terminoViagem, ipCliente, agenteUser, cartoes);
	}
	
}
