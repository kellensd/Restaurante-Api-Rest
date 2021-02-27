package votacao.api.rest.advice;

public class ValidationConstraintsError implements VotacaoError {
    private String mensagem;
    private String campo;

    public ValidationConstraintsError(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    public String getCampo() {
        return campo;
    }
}
