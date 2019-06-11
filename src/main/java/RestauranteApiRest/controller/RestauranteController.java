package RestauranteApiRest.controller;

import RestauranteApiRest.domain.commands.ComandoVotar;
import RestauranteApiRest.service.RestauranteService;
import RestauranteApiRest.entity.Restaurante;
import io.swagger.annotations.Api;
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
@Api(tags = {"Votacões"})
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    @ApiOperation(value = "Exibe lista de todos restaurantes cadastrados.")
    public List<Restaurante> findAll() {
        return restauranteService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consulta dados do restaurante informado por id.")
    public Restaurante findById(@PathVariable Long id) {
        return restauranteService.findById(id);
    }

    @GetMapping(value = "/maisVotados")
    @ApiOperation(value = "Consulta os restaurantes mais votados.")
    public List<Map<String, String>> listMaisVotados() {
        return restauranteService.findMaisVotados();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Votar em um restaurante informando nome do mesmo, nome do profissional e descrição.")
    public ResponseEntity votar(
            @RequestBody @Valid ComandoVotar votacao) {
        return restauranteService.insertVotoRestaurante(votacao);
    }
}
