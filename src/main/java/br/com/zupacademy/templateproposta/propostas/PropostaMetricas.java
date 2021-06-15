package br.com.zupacademy.templateproposta.propostas;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@Component
public class PropostaMetricas {
	
	private final MeterRegistry registroMetricas;
	private Counter contadorPropostas;
	private Timer tempoPropostas;
	
	public PropostaMetricas(MeterRegistry registro) {
		this.registroMetricas = registro;
		this.iniciarContador();
	}

	private void iniciarContador() {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("metricas1", "proposta-api1"));
		tags.add(Tag.of("metricas2", "proposta-api2"));
		
		this.contadorPropostas = this.registroMetricas.counter("proposta_criada", tags);
		this.tempoPropostas = this.registroMetricas.timer("tempo_proposta", tags);
	}
	
	public void incrementaNovaProposta() {
		this.contadorPropostas.increment();
	}
	
	public void incrementaTempoProposta(Duration tempo) {
		this.tempoPropostas.record(tempo);
	}

}
