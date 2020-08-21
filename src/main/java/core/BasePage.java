package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static core.DriverFactory.getDriver;

public class BasePage {

    /*================TextField e TextArea===================*/

    public void escreve(By by, String texto) {

        getDriver().findElement(by).clear(); // Caso seja necessário escrever mais de uma vez no mesmo campo
        getDriver().findElement(by).sendKeys(texto);
    }

    public void escreve(String id_campo, String texto) {

        getDriver().findElement(By.id(id_campo)).clear();
        getDriver().findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo) {

        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    /*==================RADIO E CHECK===================*/

    public void clicarRadio(String id) {

        getDriver().findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id) {

        return getDriver().findElement(By.id(id)).isSelected(); //Confere se o elemento foi utilizado
    }

    /*=====================COMBO========================*/

    public void selecionarCombo(String id, String valor) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor); // Metodo mais usual, se declara o valor que é apresentado no combo
    }

    public void deselecionarCombo(String id, String valor) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresCombo(String id) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> allSelectOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for (WebElement opcao : allSelectOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadedeOpcoesCombo(String id) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verficarOpcaoCombo(String id, String opcao) {

        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(opcao)) {
                return true;
            }
        }
        return false;
    }

    /*================BOTÃO===================*/

    public void clicarBotao(By by) {

        getDriver().findElement(by).click();
    }

    public void clicarBotão(String id) {

        getDriver().findElement(By.id(id)).click();
    }

    public void clicarBotaoPorTexto(String texto){

        clicarBotao(By.xpath("//button[.='"+texto+"']"));
    }

    public String obterValueElemento(String id) {

        return getDriver().findElement(By.id(id)).getAttribute("value");
    }

    /*================LINKS===================*/

    public void clicarLink(String link) {

        getDriver().findElement(By.linkText(link)).click();
    }

    /*================TEXTOS===================*/

    public String obterTexto(By by) {

        return getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {

        return obterTexto(By.id(id));
    }

    /*================ALERTAS==================*/

    public String alertaObterTexto() {

        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public String alertaObterTextoAceita() {

        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
    }

    public String alertaObterTextoENega() {

        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;
    }

    public void alertaEscrever(String valor) {

        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys(valor);
        alert.accept();
    }

    /*================FRAMES E JANELAS==================*/

    public void entrarFrame(String id) {

        getDriver().switchTo().frame(id);
    }

    public void sairFrame() {

        getDriver().switchTo().defaultContent();
    }

    public void trocarJanela(String id) {

        getDriver().switchTo().window(id); // troca para janela popup
    }

    public void fecharJanela() {
        getDriver().close();
    }

    /*================JS==================*/

    public Object executarJS(String comando, Object... parametros) {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(comando, parametros);
    }

    /*================TABELA==================*/

    public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela){

        //procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);

        //encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

        //procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        //clicar no botao da celula encontrada
        WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
        return celula;
    }

    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
        WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
        celula.findElement(By.xpath(".//input")).click();

    }

    protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
        int idLinha = -1;
        for(int i = 0; i < linhas.size(); i++) {
            if(linhas.get(i).getText().equals(valor)) {
                idLinha = i+1;
                break;
            }
        }
        return idLinha;
    }

    protected int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for(int i = 0; i < colunas.size(); i++) {
            if(colunas.get(i).getText().equals(coluna)) {
                idColuna = i+1;
                break;
            }
        }
        return idColuna;
    }
}
