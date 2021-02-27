package votacao.api.rest.common.exceptions;

public class DailyRestaurantVoteLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DailyRestaurantVoteLimitException(String message) {
        super(message);
    }
}