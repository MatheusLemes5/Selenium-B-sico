package testes;

import core.BaseTest;
import core.Propiedades;
import org.junit.Assert;
import org.junit.Test;
import page.ContasPage;
import page.MenuPage;

public class RemoverMovimentacaoContaTeste extends BaseTest {

    MenuPage menuPage = new MenuPage();
    ContasPage contasPage = new ContasPage();

    @Test
    public void testeExcluirContaComMovimentacao(){

        menuPage.acessarTelaListarConta();

        contasPage.clicarExcluirConta("Conta com movimentacao");

        Assert.assertEquals("Conta em uso na movimentações" , contasPage.obterMensagemErro());
    }
}
