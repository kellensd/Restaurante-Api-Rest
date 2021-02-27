package VotacaoApiRest.service;

import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.domain.model.Votacao;

import java.util.List;
import java.util.Map;

public interface VotacaoService {

    List<Votacao> findAll();
    Votacao findById(Long id);
    List<Map<String, String>> findRestaurantesVotados();
    void votar(ComandoVotar votacao);
}
