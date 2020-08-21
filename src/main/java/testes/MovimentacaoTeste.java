package testes;

import core.BaseTest;
import core.Propiedades;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import page.ContasPage;
import page.MenuPage;
import page.MovimentacaoPage;
import util.DataUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MovimentacaoTeste extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private MovimentacaoPage movPage = new MovimentacaoPage();

    @Test
    public void teste1_InserirMovimentacao(){

        menuPage.acessarTelaInserirMovimentacao();
        //Inseri a conta com a data de hoje
        movPage.setDataMovimentacao(DataUtils.obterDataFormatada(new Date()));
        movPage.setDataPagamento(DataUtils.obterDataFormatada(new Date()));

        movPage.setDescricao("Movimentação do Teste");
        movPage.setInterssado("Interessado Qualquer");
        movPage.setValor("500");
        movPage.setConta("Conta para movimentacoes");
        movPage.setStatusPago();
        movPage.salvar();

        Assert.assertEquals("Movimentação adicionada com sucesso!" , movPage.obterMensagemSucesso());
    }

    @Test
    public void teste2_CamposObrigatorios(){

        menuPage.acessarTelaInserirMovimentacao();

        movPage.salvar();

        List<String> erros = movPage.obterErros();
        Assert.assertTrue(erros.containsAll(Arrays.asList("Data da Movimentação é obrigatório" ,
                "Data do pagamento é obrigatório", "Descrição é obrigatório" , "Interessado é obrigatório",
                "Valor é obrigatório" , "Valor deve ser um número")));

        //Verifica que se tem 6 erros
        Assert.assertEquals(6 , erros.size());
    }

    @Test
    public void teste3_InserirMovimentacaoFutura(){

        menuPage.acessarTelaInserirMovimentacao();
        //Soma 5 dias a data do dia de hoje
        Date dataFutura = DataUtils.obterDataComDiferencaDias(5);

        movPage.setDataMovimentacao(DataUtils.obterDataFormatada(dataFutura));
        movPage.setDataPagamento(DataUtils.obterDataFormatada(dataFutura));

        movPage.setDescricao("Movimentação Do Teste");
        movPage.setInterssado("Interessado Qualquer");
        movPage.setValor("500");
        movPage.setConta("Conta para movimentacoes");
        movPage.setStatusPago();
        movPage.salvar();

        List<String> erros = movPage.obterErros();
        Assert.assertTrue(erros.containsAll(Arrays.asList("Data da Movimentação deve ser menor ou igual à data atual")));

        //Verifica que se tem 6 erros
        Assert.assertEquals(1 , erros.size());
    }
}
