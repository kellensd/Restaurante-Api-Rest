package VotacaoApiRest.common.exceptions;

public class UnknownSQLException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnknownSQLException(String message) {
        super(message);
    }
}