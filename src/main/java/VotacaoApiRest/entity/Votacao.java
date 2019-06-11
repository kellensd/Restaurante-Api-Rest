package VotacaoApiRest.entity;

import java.time.LocalDate;

public class Votacao {

    private Long id;
    private String nomeRestaurante;
    private LocalDate dataVotacao;
    private String nomeProfissional;
    private Integer voto;
    private String descricao;

    public Votacao() {
        this.dataVotacao = LocalDate.now();
        this.voto = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public LocalDate getDataVotacao() {
        return dataVotacao;
    }

    public void setDataVotacao(LocalDate dataVotacao) {
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
