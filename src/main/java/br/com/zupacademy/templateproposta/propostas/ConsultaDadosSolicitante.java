package br.com.zupacademy.templateproposta.propostas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consultaDadosSolicitante", url = "${servico.analise.solicitante}")
public interface ConsultaDadosSolicitante {

	@RequestMapping(method = RequestMethod.POST, value = "api/solicitacao",
					  consumes = "application/json",produces = "application/json")
	ConsultaDadosSolicitanteDto analisar(ConsultaDadosSolicitanteForm form);
}
