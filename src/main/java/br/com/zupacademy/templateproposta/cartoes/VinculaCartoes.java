package br.com.zupacademy.templateproposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "vinculaCartoes",url = "${servico.recursos.cartoes}")
public interface VinculaCartoes {

	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes",
			  consumes = "application/json",produces = "application/json")
	VinculaCartoesDto vincular(VinculaCartoesForm form);
	
	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes/{idCartao}/bloqueios",
			  						consumes = "application/json",produces = "application/json")
	NotificaBloqueioDto notificar(@PathVariable("idCartao") String idCartao, 
										@RequestBody NotificarBloqueioForm form );
}
