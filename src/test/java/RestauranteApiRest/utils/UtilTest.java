package RestauranteApiRest.utils;

import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class UtilTest {

    private Util util = new Util();

    @Test
    public void getDataAtualTest() {
        LocalDate dataEsperada = LocalDate.now();
        LocalDate dataAtual = util.getDataAtual();
        Assert.assertEquals(dataEsperada, dataAtual);
    }

    @Test
    public void getDataTest() {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataEsperada = formatar.format(new Date());
        String dataAtual = util.getData(new Date());
        Assert.assertEquals(dataEsperada, dataAtual);
    }

    @Test
    public void validaSeDatasSaoDaMesmaSemanaFalseTest() {
        LocalDate dataBanco = LocalDate.now().plusDays(15);
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertFalse(util.validaSeDatasSaoDaMesmaSemana(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void validaSeDatasSaoDaMesmaSemanaTest() {
        LocalDate dataBanco = LocalDate.now();
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertTrue(util.validaSeDatasSaoDaMesmaSemana(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void validaSeDatasSaoIguaisFalseTest() {
        LocalDate dataBanco = LocalDate.now().plusDays(5);
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertFalse(util.validaSeDatasSaoIguais(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void validaSeDatasSaoIguaisTest() {
        LocalDate dataBanco = LocalDate.now();
        LocalDate dataDaVotacaoAtual = LocalDate.now();
        Assert.assertTrue(util.validaSeDatasSaoIguais(dataBanco, dataDaVotacaoAtual));
    }

    @Test
    public void asDateTest() {
        Date dataAtual = util.asDate(LocalDate.of(2019, 05, 12));
        Assert.assertEquals("Sun May 12 00:00:00 BRT 2019", dataAtual.toString());
    }
}