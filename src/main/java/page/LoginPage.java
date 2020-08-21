package page;

import core.BasePage;
import core.DriverFactory;
import org.openqa.selenium.By;

import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {

    public void acessarTelaInicial(){

        getDriver().get("https://srbarriga.herokuapp.com");

    }

    public void setEmail(String email){

        escreve("email" , email);
    }

    public void setSenha(String senha){

        escreve("senha" , senha);
    }

    public void clicaEntar() {

        clicarBotaoPorTexto("Entrar");
        //clicarBotao(By.xpath("//button[@class=\"btn btn-primary\"]"));
    }

    /*Opção que faz o login completo*/
    public void logar(String email, String senha){

        escreve("email" , email);
        escreve("senha" , senha);
        clicarBotaoPorTexto("Entrar");
        //clicarBotao(By.xpath("//button[@class=\"btn btn-primary\"]"));
    }

    public void resetar(){

        clicarLink("reset");
    }
}
