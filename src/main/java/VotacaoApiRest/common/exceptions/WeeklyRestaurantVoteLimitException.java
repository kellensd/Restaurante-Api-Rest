package VotacaoApiRest.common.exceptions;

public class WeeklyRestaurantVoteLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WeeklyRestaurantVoteLimitException(String message) {
        super(message);
    }
}