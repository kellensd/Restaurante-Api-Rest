package VotacaoApiRest.service;

import VotacaoApiRest.common.exceptions.DailyRestaurantVoteLimitException;
import VotacaoApiRest.common.exceptions.WeeklyRestaurantVoteLimitException;
import VotacaoApiRest.common.validations.DataValidation;
import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.domain.model.Votacao;
import VotacaoApiRest.repository.VotacaoRepository;
import VotacaoApiRest.utils.ExtractResultDatabase;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VotacaoService implements IVotacaoService {

    private JdbcTemplate jtm;
    private VotacaoRepository votacaoRepository;

    public VotacaoService(JdbcTemplate jtm, VotacaoRepository votacaoRepository) {
        this.jtm = jtm;
        this.votacaoRepository = votacaoRepository;
    }

    @Override
    public List<Votacao> findAll() {
        String sql = "SELECT * FROM VOTACAO";
        List<Votacao> votacoes = jtm.query(sql, new BeanPropertyRowMapper(Votacao.class));
        return votacoes;
    }

    @Override
    public Votacao findById(Long id) {
        String sql = "SELECT * FROM VOTACAO WHERE ID=?";
        Votacao votacao = (Votacao) jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper(Votacao.class));
        return votacao;
    }

    @Override
    public List<Map<String, String>> findMaisVotados() {
        String sql = "SELECT nomeRestaurante as restaurante, count(voto) as votos FROM VOTACAO group by nomeRestaurante";
        return ExtractResultDatabase.getListMapDeDados(jtm, sql);
    }

    public ResponseEntity<String> votar(ComandoVotar votacao) {
        List<Votacao> listRestaurantesBanco = findAll();
        for (Votacao votacaoBanco : listRestaurantesBanco) {
            if (votacaoBanco.getNomeProfissional().equalsIgnoreCase(votacao.getNomeProfissional())
                    && DateUtils.isSameDay(votacaoBanco.getDataVotacao(), new Date())){
                throw new DailyRestaurantVoteLimitException("Erro! Você só pode votar em um restaurante por dia!");
            }

            if (votacaoBanco.getNomeRestaurante().equalsIgnoreCase(votacao.getNomeRestaurante())
                    && DataValidation.isSameWeek(votacaoBanco.getDataVotacao(), new Date())) {
                throw new WeeklyRestaurantVoteLimitException("Erro! O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana!");
            }
        }

        Votacao votacaoDatabase = new Votacao();
        votacaoDatabase.setDescricao(votacao.getDescricao());
        votacaoDatabase.setNomeProfissional(votacao.getNomeProfissional());
        votacaoDatabase.setNomeRestaurante(votacao.getNomeRestaurante());

        votacaoRepository.save(votacaoDatabase);

        return ResponseEntity.status(HttpStatus.CREATED).body("Voto realizado com sucesso!");
    }
}
