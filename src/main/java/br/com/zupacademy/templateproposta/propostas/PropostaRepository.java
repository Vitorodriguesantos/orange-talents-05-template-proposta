package br.com.zupacademy.templateproposta.propostas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByCPFouCNPJ(String cpFouCNPJ);

	@Query("select p from Proposta p where p.status = 'ELEGIVEL' and p.cartao is null")
	List<Proposta> findElegiveis();

}
