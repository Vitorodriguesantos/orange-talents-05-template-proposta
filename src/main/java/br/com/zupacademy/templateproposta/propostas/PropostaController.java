package br.com.zupacademy.templateproposta.propostas;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController @RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder){
		Proposta aProposta = form.converter();
		propostaRepository.save(aProposta);
		System.out.println(aProposta.getId());
		return ResponseEntity.created(uriBuilder.path("/propostas/{id}").buildAndExpand(aProposta.getId()).toUri()).body(aProposta);
	}

}
