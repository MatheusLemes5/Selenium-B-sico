package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    /*Metodo usado para adicionar o subtrair o numero dos dias*/
    public static Date obterDataComDiferencaDias(int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH , dias);
        return calendar.getTime();
    }

    /*Formata como deve ser aceita a data*/
    public static String obterDataFormatada(Date data){

        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        return format.format(data);
    }
}
