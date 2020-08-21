package testes;

import core.BaseTest;
import core.DriverFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.MenuPage;
import page.ResumoPage;

import java.util.List;

import static core.DriverFactory.*;

public class ResumoTeste extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private ResumoPage resumoPage = new ResumoPage();

    @Test
    public void teste1_excluirMovimentacao(){

        menuPage.acessarTelaResumo();

        resumoPage.excluirMovimentacao();

        Assert.assertEquals("Movimentação removida com sucesso!" , resumoPage.obterMensagemSucesso());

    }

    @Test
    public void teste2_ResumoMensal(){

        menuPage.acessarTelaResumo();
        //Testa o nome da aba do chrome.
        Assert.assertEquals("Seu Barriga - Extrato" , getDriver().getTitle());

        resumoPage.selecionarAno("2019");
        resumoPage.buscar();

        List<WebElement> elementosEncontrados =
                DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
        Assert.assertEquals(0, elementosEncontrados.size());

    }
}
