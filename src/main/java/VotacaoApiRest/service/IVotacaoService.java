package VotacaoApiRest.service;

import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.domain.model.Votacao;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IVotacaoService {

    List<Votacao> findAll();
    Votacao findById(Long id);
    List<Map<String, String>> findMaisVotados();
    ResponseEntity<String> votar(ComandoVotar votacao);
}
