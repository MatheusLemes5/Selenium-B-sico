package testes;

import core.BaseTest;
import core.Propiedades;
import org.junit.Assert;
import org.junit.Test;
import page.HomePage;
import page.MenuPage;

public class SaldoTeste extends BaseTest {

    MenuPage menuPage = new MenuPage();
    HomePage page = new HomePage();

    @Test
    public void testeSaldoConta(){

        menuPage.acessasTelaPrincipal();
        Assert.assertEquals("534.00" , page.obterSaldoConta("Conta para saldo"));
    }
}
