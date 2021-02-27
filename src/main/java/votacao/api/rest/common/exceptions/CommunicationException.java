package votacao.api.rest.common.exceptions;

public class CommunicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CommunicationException(String message) {
        super(message);
    }
}
