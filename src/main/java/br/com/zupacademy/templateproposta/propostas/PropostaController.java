package br.com.zupacademy.templateproposta.propostas;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.templateproposta.validacao.EncryptDocumento;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController @RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private ConsultaDadosSolicitante consultaDadosSolicitante;
	
	@Autowired
	private PropostaMetricas propostaMetricas;
	
	@Autowired
	private Tracer tracer;
	
	@GetMapping("/{id}")
	public ResponseEntity<PropostaDto> buscarProposta(@PathVariable Long id){
		final Long inicioExecucao = System.currentTimeMillis();
		Optional<Proposta> aProposta = propostaRepository.findById(id);
		if(aProposta.isPresent()) {
			return ResponseEntity.ok(new PropostaDto(aProposta.get()));
		}
			final Long fimExecucao = System.currentTimeMillis();
			final Long tempoExecucao = fimExecucao - inicioExecucao;
			propostaMetricas.incrementaTempoProposta(Duration.of(tempoExecucao,ChronoUnit.MILLIS));
			return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder){
		
		Span oSpan = tracer.activeSpan();
		oSpan.setTag("testeTag", "propostaTag");
		oSpan.setBaggageItem("testeBaggage", "propostaBaggage");
		oSpan.log("TesteLog");
		
		Optional<Proposta> possivelProposta = propostaRepository.findByCPFouCNPJ(EncryptDocumento.encriptDocumento(form.getCPFouCNPJ()));
		if(possivelProposta.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		//criando nova proposta
		Proposta aProposta = criandoNovaProposta(form);
		
		//validando nova proposta
		ConsultaDadosSolicitanteDto retornoValidacao = verificaDadosSolicitante(aProposta);
		aProposta.setStatus(MapearStatusProposta.mStatus.get(retornoValidacao.getResultadoSolicitacao()));
		propostaRepository.save(aProposta);
		propostaMetricas.incrementaNovaProposta();
		return ResponseEntity.created(uriBuilder.path("/propostas/{id}").buildAndExpand(aProposta.getId()).toUri()).body(aProposta);
	}

	private Proposta criandoNovaProposta(@Valid PropostaForm form) {
		Proposta aProposta = form.converter();
		propostaRepository.save(aProposta);
		return aProposta;
	}

	private ConsultaDadosSolicitanteDto verificaDadosSolicitante(Proposta aProposta) {

		ConsultaDadosSolicitanteForm consultaDadosSolicitanteForm = new ConsultaDadosSolicitanteForm(aProposta.getCPFouCNPJ(), aProposta.getNome(), aProposta.getId().toString());
		try { 
			ConsultaDadosSolicitanteDto consultaDadosSolicitanteDto = consultaDadosSolicitante.analisar(consultaDadosSolicitanteForm);
			return consultaDadosSolicitanteDto;
			
		}catch (FeignException.UnprocessableEntity exception) {
			return new ConsultaDadosSolicitanteDto(aProposta.getCPFouCNPJ(), aProposta.getNome(), "COM_RESTRICAO", aProposta.getId().toString());
		}
	}

}
