package suites;

import core.DriverFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import page.LoginPage;
import testes.*;

import static core.DriverFactory.*;
import static org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        ContaTeste.class,
        MovimentacaoTeste.class,
        RemoverMovimentacaoContaTeste.class,
        SaldoTeste.class,
        ResumoTeste.class
})
public class SuiteGeral {

    private static LoginPage loginPage = new LoginPage();

    @BeforeClass
    public static void reset(){

        loginPage.acessarTelaInicial();

        loginPage.setEmail("mapi.lemes1@outlook.com");
        loginPage.setSenha("123456");
        loginPage.clicaEntar();

        loginPage.resetar();

        killDriver();
    }

}
