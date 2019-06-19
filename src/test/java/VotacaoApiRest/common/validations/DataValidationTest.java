package VotacaoApiRest.common.validations;

import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DataValidationTest {

    @Test
    public void isDatasDaMesmaSemanaTest1() {
        LocalDate dataBanco = LocalDate.now().plusDays(15);
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertFalse("Datas não são da mesma semana.", DataValidation.isDatasDaMesmaSemana(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void isDatasDaMesmaSemanaTest2() {
        LocalDate dataBanco = LocalDate.now();
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertTrue("Datas são da mesma semana.", DataValidation.isDatasDaMesmaSemana(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void isDatasIguaisTest1() {
        LocalDate dataBanco = LocalDate.now().plusDays(5);
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertFalse("Datas não são iguais.", DataValidation.isDatasIguais(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void isDatasIguaisTest2() {
        LocalDate dataBanco = LocalDate.now();
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertTrue("Datas são iguais.", DataValidation.isDatasIguais(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void getDataPadraoDdMmYyyyTest() {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataEsperada = formatar.format(new Date());
        String dataAtual = DataValidation.getDataPadraoDdMmYyyy(new Date());
        Assert.assertEquals(dataEsperada, dataAtual);
    }

    @Test
    public void converteLocalDateParaDateTest() {
        Date dataAtual = DataValidation.converteLocalDateParaDate(LocalDate.of(2019, 05, 12));
        Assert.assertEquals("Sun May 12 00:00:00 BRT 2019", dataAtual.toString());
    }
}