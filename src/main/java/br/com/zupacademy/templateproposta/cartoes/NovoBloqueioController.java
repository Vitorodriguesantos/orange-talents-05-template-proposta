package br.com.zupacademy.templateproposta.cartoes;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovoBloqueioController {

	@Autowired
	private BloqueiosRepository bRepository;
	@Autowired
	private CartoesRepository cRepository;
	@Autowired
	private VinculaCartoes vinculaCartoes;
	
	@PostMapping("/cartoes/{idCartao}/bloqueio")
	@Transactional
	public ResponseEntity<?> novoBloqueio(@PathVariable String idCartao, HttpServletRequest httpServletRequest) throws Exception{
		
		Optional<Cartoes> oCartao = cRepository.findById(idCartao);
		if(oCartao.isEmpty()){
			return ResponseEntity.notFound().build();
		
		}
		
		if(oCartao.get().estaBloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		//bloqueando cartão
		String ipCliente = httpServletRequest.getRemoteAddr();
		String agenteUser = httpServletRequest.getHeader("User-Agent");
		Bloqueios oBloqueio = new Bloqueios(ipCliente, agenteUser, oCartao.get());
		
		//notificando sistema legado
		notificaSistemaLegado(oCartao.get());
	
		bRepository.save(oBloqueio);
		oCartao.get().setStatusCartao(StatusCartao.BLOQUEADO);
		cRepository.save(oCartao.get());
		return ResponseEntity.ok().build();
	}

	private void notificaSistemaLegado(Cartoes cartoes) throws Exception {
		try {
		NotificarBloqueioForm notificaBloqueio = new NotificarBloqueioForm("template-proposta");
		NotificaBloqueioDto notificaBloqueioResposta = vinculaCartoes.notificar(cartoes.getId(), notificaBloqueio);
		} catch (Exception e) {
			throw new Exception("Falha ao notificar sistema legado. "+e);
		}
	}
}
