package votacao.api.rest.common.exceptions;

public class VotacaoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VotacaoNotFoundException(String message) {
        super(message);
    }
}
