package votacao.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"votacao.api.rest"})
public class VotacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacoesApplication.class, args);
	}
}
