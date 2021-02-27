package votacao.api.rest.domain.model;

import votacao.api.rest.domain.commands.ComandoVotar;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VOTACAO")
public class Votacao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTACAO_ID_SEQ")
    @SequenceGenerator(name="VOTACAO_ID_SEQ", sequenceName = "VOTACAO_ID_SEQ", allocationSize = 1, initialValue = 5)
    private Long id;

    @Column(name = "NOME_RESTAURANTE")
    private String nomeRestaurante;

    @Column(name = "DATA_VOTACAO")
    private Date dataVotacao;

    @Column(name = "NOME_PROFISSIONAL")
    private String nomeProfissional;

    @Column(name = "VOTO")
    private Integer voto;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Votacao() {
    }

    public Votacao(ComandoVotar voto) {
        this.dataVotacao = new Date();
        this.voto = 1;
        this.descricao = voto.getDescricao();
        this.nomeRestaurante = voto.getNomeRestaurante();
        this.nomeProfissional = voto.getNomeProfissional();
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

    public Date getDataVotacao() {
        return dataVotacao;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public Integer getVoto() {
        return voto;
    }

    public String getDescricao() {
        return descricao;
    }
}
