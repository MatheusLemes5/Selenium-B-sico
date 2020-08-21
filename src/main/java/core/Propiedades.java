package core;

public class Propiedades {

    public static boolean FECHAR_BROWSER = true;

    public static Browser BROWSER = Browser.CHROME;

    public static String NOME_CONTA_ALTERADA = "Conta do Teste Alterada" + System.nanoTime();

    public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.NUVEM;

    public enum Browser{
            CHROME,
            FIREFOX
    }

    public enum TipoExecucao{
        LOCAL,
        GRID,
        NUVEM
    }
}
