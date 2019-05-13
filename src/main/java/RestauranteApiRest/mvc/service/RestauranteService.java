package RestauranteApiRest.mvc.service;

import RestauranteApiRest.mvc.entity.Restaurante;
import RestauranteApiRest.mvc.utils.Util;
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

@Service
public class RestauranteService implements IRestauranteService  {

    private Util util = new Util();

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Restaurante> findAll() {
        String sql = "SELECT * FROM RESTAURANTE";
        List<Restaurante> restaurantes = jtm.query(sql, new BeanPropertyRowMapper(Restaurante.class));
        return restaurantes;
    }

    @Override
    public Restaurante findById(Long id) {
        String sql = "SELECT * FROM RESTAURANTE WHERE ID=?";
        Restaurante restaurante = (Restaurante) jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper(Restaurante.class));
        return restaurante;
    }

    @Override
    public ResponseEntity<String> insertVotoRestaurante(String nomeRestaurante, String nomeProfissional, String descricao) {

        int countDia = 0, countSemana = 0;
        List<Restaurante> listRestaurantesBanco = findAll();
        for (Restaurante restauranteBanco : listRestaurantesBanco) {
            if (restauranteBanco.getNomeProfissional().equalsIgnoreCase(nomeProfissional)
                    && util.validaSeDatasSaoIguais(restauranteBanco.getDataVotacao(), LocalDate.now())) {
                countDia++;
            }

            if (restauranteBanco.getNomeRestaurante().equalsIgnoreCase(nomeRestaurante.replace("_", " "))
                    && util.validaSeDatasSaoDaMesmaSemana(restauranteBanco.getDataVotacao(), LocalDate.now())) {
                countSemana++;
            }
        }
        if (countDia > 0) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Erro! Você só pode votar em um restaurante por dia!");
        } else if (countSemana > 0) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Erro! O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana!");
        }

        String sql = "INSERT INTO RESTAURANTE(nomeRestaurante, nomeProfissional, voto, descricao) VALUES(?, ?, 1, ?); ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jtm.getDataSource().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nomeRestaurante);
            preparedStatement.setString(2, nomeProfissional);
            preparedStatement.setString(3, descricao);
            preparedStatement.execute();
        } catch (SQLException e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir votação: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Voto realizado com sucesso!");
    }
}
