package RestauranteApiRest.mvc.service;

import RestauranteApiRest.mvc.entity.Restaurante;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IRestauranteService {

    List<Restaurante> findAll();
    Restaurante findById(Long id);
    ResponseEntity<String> insertVotoRestaurante(String nomeRestaurante, String nomeProfissional, String descricao);
}
