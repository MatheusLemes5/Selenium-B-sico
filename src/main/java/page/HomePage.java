package page;

import core.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public String obterSaldoConta(String string){

        return obterCelula("Conta", string, "Saldo", "tabelaSaldo").getText();
    }
}
