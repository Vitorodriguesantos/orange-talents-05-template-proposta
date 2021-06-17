package br.com.zupacademy.templateproposta.viagens;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.templateproposta.cartoes.Cartoes;
import br.com.zupacademy.templateproposta.cartoes.CartoesRepository;
import br.com.zupacademy.templateproposta.cartoes.VinculaCartoes;
import br.com.zupacademy.templateproposta.validacao.ErroServicosCodigo;

@RestController
public class NovaViagemController {
	
	@Autowired
	private ViagemRepository vRepository;
	
	@Autowired
	private CartoesRepository cRepository;
	
	@Autowired VinculaCartoes vCartoes;

	@PostMapping("viagens/{idCartao}")
	public ResponseEntity<?> novaViagem(@PathVariable String idCartao, 
											@RequestBody @Valid ViagemForm form,
												HttpServletRequest httpServletRequest) throws ErroServicosCodigo{
		
		Optional<Cartoes> oCartao = cRepository.findById(idCartao);
		if(oCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		String ipCliente = httpServletRequest.getRemoteAddr();
		String userAgente = httpServletRequest.getHeader("User-Agent");
		Viagem aViagem = form.converter(oCartao.get(), ipCliente, userAgente);
		notificaSistemaViagens(aViagem,oCartao.get());
		vRepository.save(aViagem);
		return ResponseEntity.ok().build();
	}

	private void notificaSistemaViagens(Viagem aViagem, Cartoes cartao) throws ErroServicosCodigo {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		NotificaSistemaBancarioForm notificaViagem = new NotificaSistemaBancarioForm(aViagem.getDestino(), aViagem.dataTerminoFormatada(formato));
		try {
			vCartoes.notificarViagem(cartao.getId(), notificaViagem);
			
		} catch (Exception e) {
			throw new ErroServicosCodigo(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel comunicar com o sistema de pagamentos."+e);
		}
		
		
	}
}
