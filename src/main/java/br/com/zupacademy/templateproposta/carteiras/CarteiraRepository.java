package br.com.zupacademy.templateproposta.carteiras;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<CarteiraDigital, String>{

	Optional<CarteiraDigital> findByNome(NomeCarteira nome);

}
