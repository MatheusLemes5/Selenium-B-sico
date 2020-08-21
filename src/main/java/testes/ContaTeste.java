package testes;

import core.BaseTest;
import core.Propiedades;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import page.ContasPage;
import page.MenuPage;


public class ContaTeste extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private ContasPage contasPage = new ContasPage();

    @Test
    public void teste1_InserirConta() {

        menuPage.acessarTelaInserirConta();
        contasPage.setNome("Conta do Teste");
        contasPage.salvar();

        Assert.assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
    }

    @Test
    public void teste2_AlterarConta() {

        menuPage.acessarTelaListarConta();

        contasPage.clicarAlterarConta("Conta para alterar");

        contasPage.setNome("Conta alterada");
        contasPage.salvar();
        Assert.assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());

    }

    @Test
    public void teste3_inserirContaMesmoNome() {

        menuPage.acessarTelaInserirConta();

        contasPage.setNome("Conta mesmo nome");
        contasPage.salvar();

        Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());

    }

}
