package RestauranteApiRest.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ComandoVotar {

    @NotNull
    @NotBlank
    private String nomeRestaurante;

    @NotNull
    @NotBlank
    private String nomeProfissional;

    @NotNull
    @NotBlank
    private String descricao;

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
