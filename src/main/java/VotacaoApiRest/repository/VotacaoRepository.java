package VotacaoApiRest.repository;

import VotacaoApiRest.domain.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    List<Votacao> findAll();
}
