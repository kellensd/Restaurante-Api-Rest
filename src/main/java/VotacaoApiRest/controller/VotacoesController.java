package VotacaoApiRest.controller;

import VotacaoApiRest.domain.commands.ComandoVotar;
import VotacaoApiRest.service.IVotacaoService;
import VotacaoApiRest.domain.model.Votacao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VotacoesController {

    @Autowired
    private IVotacaoService votacaoService;

    @GetMapping
    @ApiOperation(value = "Exibe lista de todos restaurantes cadastrados.")
    public List<Votacao> findAll() {
        return votacaoService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consulta dados do restaurante informado por id.")
    public Votacao findById(@PathVariable Long id) {
        return votacaoService.findById(id);
    }

    @GetMapping(value = "/maisVotados")
    @ApiOperation(value = "Consulta os restaurantes mais votados.")
    public List<Map<String, String>> listMaisVotados() {
        return votacaoService.findMaisVotados();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Votar em um restaurante informando nome do mesmo, nome do profissional e descrição.")
    public ResponseEntity votar(
            @RequestBody @Valid ComandoVotar votacao) {
        return votacaoService.votar(votacao);
    }
}
