package br.com.zupacademy.templateproposta.biometrias;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.cartoes.CartoesRepository;

@RestController
public class BiometriaController {
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@Autowired
	private CartoesRepository cartoesRepository;
	
	@RequestMapping(value = "/cartoes/{idCartao}/biometrias")
	public ResponseEntity<?> novaBiometria(@PathVariable ("idCartao") String idCartao, 
											@RequestBody @Valid BiometriaForm form,
											    UriComponentsBuilder uriBuilder){
		Optional<Cartoes> oCartao = cartoesRepository.findById(idCartao);
		if(oCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Biometrias aBiometria = form.novaBiometria(oCartao.get());
		biometriaRepository.save(aBiometria);
		URI path = uriBuilder.path("/biometrics/{id}").buildAndExpand(aBiometria.getId()).toUri();
		return ResponseEntity.created(path).build();
	}

}
