package VotacaoApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"VotacaoApiRest"})
public class VotacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacoesApplication.class, args);
	}
}
