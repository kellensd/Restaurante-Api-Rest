package VotacaoApiRest.common.validations;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataValidation {

    public static boolean isDatasDaMesmaSemana(Date dataBanco, Date dataDaVotacaoAtual) {
        Date primeiroDiaDaSemanaDataBanco = getPrimeiroOuUltimoDiaDaSemana(dataBanco, true);
        Date ultimoDiaDaSemanaDataBanco = DateUtils.addDays(getPrimeiroOuUltimoDiaDaSemana(dataBanco, false), 1);

        Date primeiroDiaDaSemanaDataDaVotacaoAtual = getPrimeiroOuUltimoDiaDaSemana(dataDaVotacaoAtual, true);
        Date ultimoDiaDaSemanaDataDaVotacaoAtual = getPrimeiroOuUltimoDiaDaSemana(dataDaVotacaoAtual, false);

        return primeiroDiaDaSemanaDataDaVotacaoAtual.compareTo(primeiroDiaDaSemanaDataBanco) >= 0
                && ultimoDiaDaSemanaDataDaVotacaoAtual.compareTo(ultimoDiaDaSemanaDataBanco) < 0;
    }

    private static Date getPrimeiroOuUltimoDiaDaSemana(Date data, boolean isPrimeiro) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(data);
        if (isPrimeiro) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        return calendar.getTime();
    }

    public static String getDataPadraoDdMmYyyy(Date data) {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        return formatar.format(data);
    }
}
