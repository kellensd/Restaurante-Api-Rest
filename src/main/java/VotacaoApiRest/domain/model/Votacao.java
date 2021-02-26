package VotacaoApiRest.domain.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "VOTACAO")
public class Votacao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTACAO_ID_SEQ")
    @SequenceGenerator(name="VOTACAO_ID_SEQ", sequenceName = "VOTACAO_ID_SEQ", allocationSize = 1)
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
        Calendar calendar = Calendar.getInstance();
        this.dataVotacao = calendar.getTime();
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
