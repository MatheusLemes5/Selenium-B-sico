package page;

import core.BasePage;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static core.DriverFactory.getDriver;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoPage extends BasePage {

    public void setDataMovimentacao(String data){

        escreve("data_transacao" , data);
    }

    public void setDataPagamento(String data){

        escreve("data_pagamento" , data);
    }

    public void setDescricao(String descricao){

        escreve("descricao" , descricao);
    }

    public void setInterssado(String interessado){

        escreve("interessado" , interessado);
    }

    public void setValor(String valor){

        escreve("valor" , valor);
    }

    public void setConta(String conta){

        selecionarCombo("conta" , conta);
    }

    public void setStatusPago(){

        clicarRadio("status_pago");
    }

    public void salvar(){

        clicarBotaoPorTexto("Salvar");
    }

    public String obterMensagemSucesso(){

        return obterTexto(By.xpath("//div[@class=\"alert alert-success\"]"));
    }

    /*Método com finalidade de ler todos os erros reportados que estão dentro da Div->li*/
    public List<String> obterErros(){

           List<WebElement> erros = getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
           List<String> retorno = new ArrayList<String>();
           for(WebElement erro: erros){
               retorno.add(erro.getText());
           }
           return retorno;
    }

}

