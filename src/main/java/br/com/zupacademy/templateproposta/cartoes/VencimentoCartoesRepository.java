package br.com.zupacademy.templateproposta.cartoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VencimentoCartoesRepository extends JpaRepository<VencimentoCartao, String>{

}
