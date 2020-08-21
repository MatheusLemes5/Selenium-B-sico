package page;

import core.BasePage;

public class MenuPage extends BasePage {

    public void acessarTelaInserirConta(){

        clicarLink("Contas");
        clicarLink("Adicionar");
    }

    public void acessarTelaListarConta(){

        clicarLink("Contas");
        clicarLink("Listar");
    }

    public void acessarTelaInserirMovimentacao(){

        clicarLink("Criar Movimentação");
    }

    public void acessarTelaResumo(){

        clicarLink("Resumo Mensal");
    }

    public void acessasTelaPrincipal(){

        clicarLink("Home");
    }

}
