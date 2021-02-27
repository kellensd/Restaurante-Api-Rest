package votacao.api.rest.advice;

public class ValidationHeaderError implements VotacaoError {
    private String mensagem;

    public ValidationHeaderError(String headerName, String headerType) {
        this.mensagem = "Request header '" + headerName +
                "' ausente para o parâmetro de método do tipo " + headerType;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }
}
