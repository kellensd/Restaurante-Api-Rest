package VotacaoApiRest.service;

import VotacaoApiRest.common.exceptions.DailyRestaurantVoteLimitException;
import VotacaoApiRest.common.exceptions.UnknownSQLException;
import VotacaoApiRest.common.exceptions.WeeklyRestaurantVoteLimitException;
import VotacaoApiRest.common.validations.DataValidation;
import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.entity.Votacao;
import VotacaoApiRest.utils.ExtractResultDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class VotacaoService implements IVotacaoService {

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Votacao> findAll() {
        String sql = "SELECT * FROM RESTAURANTE";
        List<Votacao> votacoes = jtm.query(sql, new BeanPropertyRowMapper(Votacao.class));
        return votacoes;
    }

    @Override
    public Votacao findById(Long id) {
        String sql = "SELECT * FROM RESTAURANTE WHERE ID=?";
        Votacao votacao = (Votacao) jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper(Votacao.class));
        return votacao;
    }

    @Override
    public List<Map<String, String>> findMaisVotados() {
        String sql = "SELECT nomeRestaurante as restaurante, count(voto) as votos FROM RESTAURANTE group by nomeRestaurante";
        return ExtractResultDatabase.getListMapDeDados(jtm, sql);
    }

    public ResponseEntity<String> votar(ComandoVotar votacao) {

        int countDia = 0, countSemana = 0;
        List<Votacao> listRestaurantesBanco = findAll();
        for (Votacao votacaoBanco : listRestaurantesBanco) {
            if (votacaoBanco.getNomeProfissional().equalsIgnoreCase(votacao.getNomeProfissional())
                    && DataValidation.isDatasIguais(votacaoBanco.getDataVotacao(), LocalDate.now())){
                countDia++;
            }

            if (votacaoBanco.getNomeRestaurante().equalsIgnoreCase(votacao.getNomeRestaurante())
                    && DataValidation.isDatasDaMesmaSemana(votacaoBanco.getDataVotacao(), LocalDate.now())) {
                countSemana++;
            }
        }
        if (countDia > 0) {
            throw new DailyRestaurantVoteLimitException("Erro! Você só pode votar em um restaurante por dia!");
        } else if (countSemana > 0) {
            throw new WeeklyRestaurantVoteLimitException("Erro! O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana!");
        }

        String sql = "INSERT INTO RESTAURANTE(nomeRestaurante, nomeProfissional, voto, descricao) VALUES(?, ?, 1, ?); ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jtm.getDataSource().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, votacao.getNomeRestaurante());
            preparedStatement.setString(2, votacao.getNomeProfissional());
            preparedStatement.setString(3, votacao.getDescricao());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UnknownSQLException("Erro ao inserir votação: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Voto realizado com sucesso!");
    }
}
