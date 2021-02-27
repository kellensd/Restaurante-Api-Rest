package votacao.api.rest.advice;

import java.util.ArrayList;
import java.util.List;

public class ErrorRepresentation {
    private Integer errorCode;
    private TipoErro typeError;
    private List<VotacaoError> constraints;


    public ErrorRepresentation(Integer errorCode, TipoErro typeError) {
        this.setErrorCode(errorCode);
        this.setTypeError(typeError);
        this.setConstraints(new ArrayList<>());
    }

    public void addErro(VotacaoError erro) {
        getConstraints().add(erro);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public TipoErro getTypeError() {
        return typeError;
    }

    public void setTypeError(TipoErro typeError) {
        this.typeError = typeError;
    }

    public List<VotacaoError> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<VotacaoError> constraints) {
        this.constraints = constraints;
    }
}