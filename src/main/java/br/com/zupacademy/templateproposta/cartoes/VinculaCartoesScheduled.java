package br.com.zupacademy.templateproposta.cartoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.templateproposta.propostas.Proposta;
import br.com.zupacademy.templateproposta.propostas.PropostaRepository;
import feign.FeignException;

@Component
public class VinculaCartoesScheduled {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private VinculaCartoes vinculaCartoes;
	@Autowired
	private CriarCartao criarCartao;
	
	@Scheduled(fixedDelayString = "${tempo.scheduled.vincular}")
	void buscarPropostasEVincular() {
		List<Proposta> asPropostas = propostaRepository.findElegiveis();
		
		asPropostas.forEach(proposta ->{
			try {
				VinculaCartoesForm aVincular = new VinculaCartoesForm(proposta.getCPFouCNPJ(), proposta.getNome(), proposta.getId().toString());
				VinculaCartoesDto vinculaCartoesDto = vinculaCartoes.vincular(aVincular);
				Cartoes oCartao = criarCartao.novoCartao(vinculaCartoesDto);
				proposta.setCartao(oCartao);
				propostaRepository.save(proposta);
			}catch (FeignException e) {
				System.out.println("ERRO EM VINCULAR ->>>>>>>>>  "+e);
			}
		});
	}
}
