package votacao.api.rest.service;

import votacao.api.rest.domain.commands.ComandoVotar;
import votacao.api.rest.domain.dto.VotacaoDTO;

import java.util.List;
import java.util.Map;

public interface VotacaoService {

    List<VotacaoDTO> findAll();
    VotacaoDTO findById(Long id);
    List<Map<String, String>> findRestaurantesVotados();
    void votar(ComandoVotar votacao);
}
