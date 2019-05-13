package RestauranteApiRest.mvc.controller;

import RestauranteApiRest.mvc.service.RestauranteService;
import RestauranteApiRest.mvc.entity.Restaurante;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @ApiOperation(value = "Exibe lista de todos restaurantes cadastrados.")
    @RequestMapping(value = "/restaurantes", method = RequestMethod.GET)
    public List<Restaurante> findAll() {
        return restauranteService.findAll();
    }

    @ApiOperation(value = "Consulta dados do restaurante informado por id.")
    @RequestMapping(value = "/restaurantes/{id}", method = RequestMethod.GET)
    public Restaurante findById(@PathVariable Long id) {
        return restauranteService.findById(id);
    }

    @ApiOperation(value = "Consulta os restaurantes mais votados.")
    @RequestMapping(value = "/restaurantes/maisVotados", method = RequestMethod.GET)
    public List<Map<String, String>> listMaisVotados() {
        return restauranteService.findMaisVotados();
    }

    @ApiOperation(value = "Votar em um restaurante informando nome do mesmo, nome do profissional e descrição.")
    @RequestMapping(value = "/restaurantes/{nomeRestaurante}/{nomeProfissional}/{descricao}", produces = { "application/json" }, method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> votar(
            @PathVariable("nomeRestaurante") String nomeRestaurante,
            @PathVariable("nomeProfissional") String nomeProfissional,
            @PathVariable("descricao") String descricao) {
        return restauranteService.insertVotoRestaurante(nomeRestaurante, nomeProfissional, descricao);
    }
}
