package votacao.api.rest.common.validations;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataValidation {

    public static boolean isSameWeek(Date dataBanco, Date dataDaVotacaoAtual) {
        Date primeiroDiaDaSemanaDataBanco = getDataDaSemana(dataBanco, Calendar.MONDAY);
        Date ultimoDiaDaSemanaDataBanco = DateUtils.addDays(getDataDaSemana(dataBanco, Calendar.SUNDAY), 1);

        Date primeiroDiaDaSemanaDataDaVotacaoAtual = getDataDaSemana(dataDaVotacaoAtual, Calendar.MONDAY);
        Date ultimoDiaDaSemanaDataDaVotacaoAtual = getDataDaSemana(dataDaVotacaoAtual, Calendar.SUNDAY);

        return primeiroDiaDaSemanaDataDaVotacaoAtual.compareTo(primeiroDiaDaSemanaDataBanco) >= 0
                && ultimoDiaDaSemanaDataDaVotacaoAtual.compareTo(ultimoDiaDaSemanaDataBanco) < 0;
    }

    private static Date getDataDaSemana(Date data, int diaDaSemana) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_WEEK, diaDaSemana);
        return calendar.getTime();
    }

    public static String getDataPadraoDdMmYyyy(Date data) {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        return formatar.format(data);
    }
}
