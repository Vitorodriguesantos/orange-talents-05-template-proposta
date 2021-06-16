package br.com.zupacademy.templateproposta.viagens;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.cartoes.CartoesRepository;

@RestController
public class NovaViagemController {
	
	@Autowired
	private ViagemRepository vRepository;
	
	@Autowired
	private CartoesRepository cRepository;

	@PostMapping("viagens/{idCartao}")
	public ResponseEntity<?> novaViagem(@PathVariable String idCartao, 
											@RequestBody @Valid ViagemForm form,
												HttpServletRequest httpServletRequest){
		
		Optional<Cartoes> oCartao = cRepository.findById(idCartao);
		if(oCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		String ipCliente = httpServletRequest.getRemoteAddr();
		String userAgente = httpServletRequest.getHeader("User-Agent");
		Viagem aViagem = form.converter(oCartao.get(), ipCliente, userAgente);
		vRepository.save(aViagem);
		return ResponseEntity.ok().build();
	}
}
