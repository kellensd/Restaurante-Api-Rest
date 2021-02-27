package VotacaoApiRest.repository;

import VotacaoApiRest.domain.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    List<Votacao> findAll();
    Optional<Votacao> findById(Long id);

    @Query(value = "SELECT NOME_RESTAURANTE as restaurante, count(VOTO) as votos FROM VOTACAO group by NOME_RESTAURANTE ORDER BY VOTOS DESC", nativeQuery = true)
    List<Map<String, String>> findRestaurantesVotados();
}
