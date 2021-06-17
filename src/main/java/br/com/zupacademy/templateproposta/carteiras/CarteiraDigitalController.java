package br.com.zupacademy.templateproposta.carteiras;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.cartoes.CartoesRepository;
import br.com.zupacademy.templateproposta.cartoes.VinculaCartoes;
import br.com.zupacademy.templateproposta.validacao.ErroServicosCodigo;

@RestController
public class CarteiraDigitalController {
	
	@Autowired
	private CartoesRepository cartaoRepository;
	@Autowired
	private CarteiraRepository carteiraRepository;
	@Autowired
	private VinculaCartoes vinculaCartoes;
	
	@PostMapping("/cartoes/{idCartao}/carteiras")
	public ResponseEntity<?> novaCarteiraDigital(@PathVariable String idCartao,
													@RequestBody @Valid CarteiraDigitalForm form,
														UriComponentsBuilder uri) throws ErroServicosCodigo{
		
		Optional<Cartoes> oCartao = cartaoRepository.findById(idCartao);
		if(oCartao.isEmpty()) {
			throw new ErroServicosCodigo(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
		}
		verificaCarteiraDuplicada(form);
		CarteiraDigital aCarteira = form.converter(oCartao.get());
		vinculaCarteiraNaConta(oCartao.get(),aCarteira);
		carteiraRepository.save(aCarteira);
		URI caminho = uri.path("/cartoes/{cardIdentifier}/carteiras/{id}").build(oCartao, aCarteira.getId());
        return ResponseEntity.created(caminho).build();
	}

	private void vinculaCarteiraNaConta(Cartoes cartao, CarteiraDigital aCarteira) throws ErroServicosCodigo {
		
		VincularCarteiraDigitalForm aVincular = new VincularCarteiraDigitalForm(aCarteira.getEmail(), aCarteira.getNome().toString()); 
		
		try {
			vinculaCartoes.vincularCarteira(cartao.getId(), aVincular);
		} catch (Exception e) {
			throw new ErroServicosCodigo(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel vincular o cartão na carteira digital.");
		}
	}

	private void verificaCarteiraDuplicada(@Valid CarteiraDigitalForm form) throws ErroServicosCodigo {
		Optional<CarteiraDigital> aCarteira = carteiraRepository.findByNome(NomeCarteira.valueOf(form.nome));
		if(aCarteira.isPresent()) {
		throw new ErroServicosCodigo(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma carteira para esse cartão");
		}
	}

}
