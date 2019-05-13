package RestauranteApiRest.mvc.service;

import RestauranteApiRest.mvc.entity.Restaurante;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IRestauranteService {

    List<Restaurante> findAll();
    Restaurante findById(Long id);
    List<Map<String, String>> findMaisVotados();
    ResponseEntity<String> insertVotoRestaurante(String nomeRestaurante, String nomeProfissional, String descricao);
}
