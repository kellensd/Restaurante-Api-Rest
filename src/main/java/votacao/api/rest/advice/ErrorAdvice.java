package votacao.api.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import votacao.api.rest.common.exceptions.*;

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
    public ResponseEntity<ErrorRepresentation> dailyRestaurantVoteLimitResponse(DailyRestaurantVoteLimitException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.UNPROCESSABLE_ENTITY.value(), TipoErro.NEGOCIO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
    }

    @ExceptionHandler(WeeklyRestaurantVoteLimitException.class)
    public ResponseEntity<ErrorRepresentation> weeklyRestaurantVoteLimitResponse(WeeklyRestaurantVoteLimitException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.UNPROCESSABLE_ENTITY.value(), TipoErro.NEGOCIO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
    }

    @ExceptionHandler(UnknownSQLException.class)
    public ResponseEntity<ErrorRepresentation> unknownSQLResponse(UnknownSQLException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.BAD_REQUEST.value(), TipoErro.DESCONHECIDO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(CommunicationException.class)
    public ResponseEntity<ErrorRepresentation> communicationResponse(CommunicationException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.INTERNAL_SERVER_ERROR.value(), TipoErro.COMUNICACAO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erros);
    }

    @ExceptionHandler(VotacaoNotFoundException.class)
    public ResponseEntity<ErrorRepresentation> votacaoNotFoundResponse(VotacaoNotFoundException ex) {
        ErrorRepresentation erros = new ErrorRepresentation(HttpStatus.NOT_FOUND.value(), TipoErro.VALIDACAO);
        erros.addErro(ex::getMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }

}
