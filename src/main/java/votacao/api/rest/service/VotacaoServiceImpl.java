package votacao.api.rest.service;

import org.apache.commons.lang.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import votacao.api.rest.common.exceptions.DailyRestaurantVoteLimitException;
import votacao.api.rest.common.exceptions.VotacaoNotFoundException;
import votacao.api.rest.common.exceptions.WeeklyRestaurantVoteLimitException;
import votacao.api.rest.common.validations.DataValidation;
import votacao.api.rest.domain.commands.ComandoVotar;
import votacao.api.rest.domain.dto.VotacaoDTO;
import votacao.api.rest.domain.model.Votacao;
import votacao.api.rest.repository.VotacaoRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    private VotacaoRepository votacaoRepository;

    private ModelMapper modelMapper;

    public VotacaoServiceImpl(VotacaoRepository votacaoRepository, ModelMapper modelMapper) {
        this.votacaoRepository = votacaoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VotacaoDTO> findAll() {
        return votacaoRepository.findAll()
                .stream()
                .map(votacao -> modelMapper.map(votacao, VotacaoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VotacaoDTO findById(Long id) {
        return votacaoRepository.findById(id)
                .map(votacao -> modelMapper.map(votacao, VotacaoDTO.class))
                .orElseThrow(() -> new VotacaoNotFoundException("Votação não encontrada."));
    }

    @Override
    public List<Map<String, String>> findRestaurantesVotados() {
        return votacaoRepository.findRestaurantesVotados();
    }

    public void votar(ComandoVotar voto) {
        List<VotacaoDTO> listRestaurantesBanco = findAll();
        for (VotacaoDTO votacao : listRestaurantesBanco) {
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
