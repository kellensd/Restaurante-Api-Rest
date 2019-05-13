package RestauranteApiRest.mvc.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util {

    public LocalDate getDataAtual() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(new Date());
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private Date descobrePrimeiroUltimoDiaDaSemana(Date data, boolean isPrimeiro) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(data);
        if (isPrimeiro)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    public String getData(Date data) {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        return formatar.format(data);
    }

    public boolean validaSeDatasSaoDaMesmaSemana(LocalDate localDateBanco, LocalDate localDateDaVotacaoAtual) {
        Date dataBanco = asDate(localDateBanco);
        Date dataDaVotacaoAtual = asDate(localDateDaVotacaoAtual);
        Date primeiroDiaDaSemanaDataBanco = descobrePrimeiroUltimoDiaDaSemana(dataBanco, true);
        Date ultimoDiaDaSemanaDataBanco = descobrePrimeiroUltimoDiaDaSemana(dataBanco, false);

        Date primeiroDiaDaSemanaDataDaVotacaoAtual = descobrePrimeiroUltimoDiaDaSemana(dataDaVotacaoAtual, true);
        Date ultimoDiaDaSemanaDataDaVotacaoAtual = descobrePrimeiroUltimoDiaDaSemana(dataDaVotacaoAtual, false);

        String semanaDataBanco = getData(primeiroDiaDaSemanaDataBanco) + " - " + getData(ultimoDiaDaSemanaDataBanco);
        String semanaDataDaVotacaoAtual = getData(primeiroDiaDaSemanaDataDaVotacaoAtual) + " - " + getData(ultimoDiaDaSemanaDataDaVotacaoAtual);

        if (semanaDataBanco.equalsIgnoreCase(semanaDataDaVotacaoAtual)) {
            return true;
        }
        return false;
    }

    public boolean validaSeDatasSaoIguais(LocalDate localDateBanco, LocalDate localDateDaVotacaoAtual) {
        Date dataBanco = asDate(localDateBanco);
        Date dataDaVotacaoAtual = asDate(localDateDaVotacaoAtual);
        if (dataBanco.equals(dataDaVotacaoAtual)) {
            return true;
        }
        return false;
    }

    public Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
