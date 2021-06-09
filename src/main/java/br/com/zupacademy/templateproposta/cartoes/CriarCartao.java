package br.com.zupacademy.templateproposta.cartoes;

import org.springframework.stereotype.Component;

@Component
public class CriarCartao {

	private CartoesRepository cartoesRepository;
	private VencimentoCartoesRepository vencimentoRepository;
	
	public CriarCartao(CartoesRepository cartoesRepository, VencimentoCartoesRepository vencimentoRepository) {
		this.cartoesRepository = cartoesRepository;
		this.vencimentoRepository = vencimentoRepository;
	}
	
	public Cartoes novoCartao(VinculaCartoesDto vinculaCartoesDto) {
		Cartoes oCartao = vinculaCartoesDto.converter();
		cartoesRepository.save(oCartao);
		VencimentoCartao venciCartao = vinculaCartoesDto.vincularVencimento(oCartao);
		vencimentoRepository.save(venciCartao);
		return oCartao;
	}
}
