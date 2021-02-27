package votacao.api.rest.domain.dto;

import java.util.Date;

public class VotacaoDTO {

    private String nomeRestaurante;

    private Date dataVotacao;

    private String nomeProfissional;

    private Integer voto;

    private String descricao;

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public Date getDataVotacao() {
        return dataVotacao;
    }

    public void setDataVotacao(Date dataVotacao) {
        this.dataVotacao = dataVotacao;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}