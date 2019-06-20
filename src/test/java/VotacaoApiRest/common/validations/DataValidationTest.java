package VotacaoApiRest.common.validations;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidationTest {

    @Test
    public void isDatasDaMesmaSemanaTest1() {
        Date dataBanco = DateUtils.addDays(new Date(), 15);
        Date dataDaVotacaoAtual = new Date();
        Assert.assertFalse("Datas não são da mesma semana.", DataValidation.isSameWeek(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void isDatasDaMesmaSemanaTest2() {
        Date dataBanco = new Date();
        Date dataDaVotacaoAtual = new Date();
        Assert.assertTrue("Datas são da mesma semana.", DataValidation.isSameWeek(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void getDataPadraoDdMmYyyyTest() {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataEsperada = formatar.format(new Date());
        String dataAtual = DataValidation.getDataPadraoDdMmYyyy(new Date());
        Assert.assertEquals(dataEsperada, dataAtual);
    }
}