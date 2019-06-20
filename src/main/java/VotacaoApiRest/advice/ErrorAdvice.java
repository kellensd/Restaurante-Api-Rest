package VotacaoApiRest.advice;

import VotacaoApiRest.common.exceptions.DailyRestaurantVoteLimitException;
import VotacaoApiRest.common.exceptions.UnknownSQLException;
import VotacaoApiRest.common.exceptions.WeeklyRestaurantVoteLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorRepresentation> missingHeaderReponse(MissingRequestHeaderException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.BAD_REQUEST.value(), TipoErro.VALIDACAO);
        erros.addErro(new ValidationHeaderError(ex.getHeaderName(), ex.getParameter().getNestedParameterType().getSimpleName()));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRepresentation> methodArgumentReponse(MethodArgumentNotValidException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.BAD_REQUEST.value(), TipoErro.VALIDACAO);
        ex.getBindingResult().getAllErrors().forEach(error ->
                erros.addErro(new ValidationConstraintsError(error.getDefaultMessage(), ((FieldError) error).getField()))
        );
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(DailyRestaurantVoteLimitException.class)
    public ResponseEntity<ErrorRepresentation> notFoundTransactionResponse(DailyRestaurantVoteLimitException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.UNPROCESSABLE_ENTITY.value(), TipoErro.NEGOCIO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
    }

    @ExceptionHandler(WeeklyRestaurantVoteLimitException.class)
    public ResponseEntity<ErrorRepresentation> notFoundTransactionResponse(WeeklyRestaurantVoteLimitException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.UNPROCESSABLE_ENTITY.value(), TipoErro.NEGOCIO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
    }

    @ExceptionHandler(UnknownSQLException.class)
    public ResponseEntity<ErrorRepresentation> unknownPLResponse(UnknownSQLException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.BAD_REQUEST.value(), TipoErro.DESCONHECIDO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.badRequest().body(erros);
    }

}
