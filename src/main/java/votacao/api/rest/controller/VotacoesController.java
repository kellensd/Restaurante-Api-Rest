package votacao.api.rest.controller;

import io.swagger.annotations.Api;
import votacao.api.rest.domain.dto.VotacaoDTO;
import votacao.api.rest.domain.commands.ComandoVotar;
import votacao.api.rest.service.VotacaoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/votacoes")
@Api(tags = "Votações")
public class VotacoesController {

    private VotacaoServiceImpl votacaoService;

    public VotacoesController(VotacaoServiceImpl votacaoService) {
        this.votacaoService = votacaoService;
    }

    @GetMapping
    @ApiOperation(value = "Exibe lista de todos restaurantes cadastrados.")
    public List<VotacaoDTO> findAll() {
        return votacaoService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consulta dados do restaurante informado por id.")
    public VotacaoDTO findById(@PathVariable Long id) {
        return votacaoService.findById(id);
    }

    @GetMapping(value = "/restaurantesVotados")
    @ApiOperation(value = "Consulta os restaurantes que tiveram votos.")
    public List<Map<String, String>> findRestaurantesVotados() {
        return votacaoService.findRestaurantesVotados();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Votar em um restaurante informando nome do mesmo, nome do profissional e descrição.")
    public ResponseEntity votar(
            @RequestBody @Valid ComandoVotar voto) {
        votacaoService.votar(voto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Voto realizado com sucesso!");
    }
}
