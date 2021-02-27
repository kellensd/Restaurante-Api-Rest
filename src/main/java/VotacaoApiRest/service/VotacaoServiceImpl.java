package VotacaoApiRest.service;

import VotacaoApiRest.common.exceptions.CommunicationException;
import VotacaoApiRest.common.exceptions.DailyRestaurantVoteLimitException;
import VotacaoApiRest.common.exceptions.WeeklyRestaurantVoteLimitException;
import VotacaoApiRest.common.validations.DataValidation;
import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.domain.model.Votacao;
import VotacaoApiRest.repository.VotacaoRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    private VotacaoRepository votacaoRepository;

    public VotacaoServiceImpl(VotacaoRepository votacaoRepository) {
        this.votacaoRepository = votacaoRepository;
    }

    @Override
    public List<Votacao> findAll() {
        return votacaoRepository.findAll();
    }

    @Override
    public Votacao findById(Long id) {
        return votacaoRepository.findById(id)
                .orElseThrow(() -> new CommunicationException("Votação não encontrada."));
    }

    @Override
    public List<Map<String, String>> findRestaurantesVotados() {
        return votacaoRepository.findRestaurantesVotados();
    }

    public void votar(ComandoVotar voto) {
        List<Votacao> listRestaurantesBanco = findAll();
        for (Votacao votacao : listRestaurantesBanco) {
            if (votacao.getNomeProfissional().equalsIgnoreCase(voto.getNomeProfissional())
                    && DateUtils.isSameDay(votacao.getDataVotacao(), new Date())){
                throw new DailyRestaurantVoteLimitException("Erro! Você só pode votar em um restaurante por dia!");
            }

            if (votacao.getNomeRestaurante().equalsIgnoreCase(voto.getNomeRestaurante())
                    && DataValidation.isSameWeek(votacao.getDataVotacao(), new Date())) {
                throw new WeeklyRestaurantVoteLimitException("Erro! O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana!");
            }
        }

        votacaoRepository.save(new Votacao(voto));
    }
}
