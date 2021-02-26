package VotacaoApiRest.service;

import VotacaoApiRest.common.exceptions.CommunicationException;
import VotacaoApiRest.common.exceptions.DailyRestaurantVoteLimitException;
import VotacaoApiRest.common.exceptions.WeeklyRestaurantVoteLimitException;
import VotacaoApiRest.common.validations.DataValidation;
import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.domain.model.Votacao;
import VotacaoApiRest.repository.VotacaoRepository;
import VotacaoApiRest.utils.ExtractResultDatabase;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    private JdbcTemplate jtm;
    private VotacaoRepository votacaoRepository;

    public VotacaoServiceImpl(JdbcTemplate jtm, VotacaoRepository votacaoRepository) {
        this.jtm = jtm;
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
    public List<Map<String, String>> findMaisVotados() {
        String sql = "SELECT nomeRestaurante as restaurante, count(voto) as votos FROM VOTACAO group by nomeRestaurante";
        return ExtractResultDatabase.getListMapDeDados(jtm, sql);
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

        Votacao votacaoDatabase = new Votacao();
        votacaoDatabase.setDescricao(voto.getDescricao());
        votacaoDatabase.setNomeProfissional(voto.getNomeProfissional());
        votacaoDatabase.setNomeRestaurante(voto.getNomeRestaurante());

        votacaoRepository.save(votacaoDatabase);
    }
}
