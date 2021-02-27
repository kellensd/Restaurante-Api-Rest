package votacao.api.rest.domain.commands;

import javax.validation.constraints.NotEmpty;

public class ComandoVotar {

    @NotEmpty(message = "Nome do restaurante deve ser preenchido.")
    private String nomeRestaurante;

    @NotEmpty(message = "Nome do profissional deve ser preenchido.")
    private String nomeProfissional;

    @NotEmpty(message = "Descrição deve ser preenchida.")
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
