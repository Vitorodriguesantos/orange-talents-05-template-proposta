package br.com.zupacademy.templateproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TemplatePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplatePropostaApplication.class, args);
	}

}
