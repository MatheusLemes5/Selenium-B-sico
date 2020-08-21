package core;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static core.Propiedades.*;
import static core.Propiedades.Browser.*;
import static core.Propiedades.Browser.CHROME;
import static core.Propiedades.Browser.FIREFOX;
import static core.Propiedades.BROWSER;

public class DriverFactory {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
        @Override
        protected synchronized WebDriver initialValue(){

            return initDriver();
        }
    };

    private DriverFactory() {}

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    public static WebDriver initDriver(){
        WebDriver driver = null;
        if(TIPO_EXECUCAO == TipoExecucao.LOCAL){
            switch (BROWSER) {
                case FIREFOX: driver = new FirefoxDriver(); break;
                case CHROME: driver = new ChromeDriver(); break;
             }
        }
        if(TIPO_EXECUCAO == TipoExecucao.GRID){
            DesiredCapabilities cap = null;
            switch (BROWSER){
                case CHROME: cap = DesiredCapabilities.chrome();break;

                case FIREFOX: cap = DesiredCapabilities.firefox();break;
            }
            try {
               driver = new RemoteWebDriver(new URL(" http://172.18.0.1:4444/wd/hub") , cap);
            }catch (MalformedURLException e){
                System.err.println("Falha na conexão com o GRID");
                e.printStackTrace();
            }
        }
        if(TIPO_EXECUCAO == TipoExecucao.NUVEM){
            DesiredCapabilities cap = null;
            switch (BROWSER){
                case CHROME: cap = DesiredCapabilities.chrome();break;

                case FIREFOX: cap = DesiredCapabilities.firefox();break;
            }
            try {
                driver = new RemoteWebDriver(new URL(" https://MatheusLemes:c124857d-75a5-4846-ad29-b13efa11ca79@ondemand.saucelabs.com:443/wd/hub") , cap);
            }catch (MalformedURLException e){
                System.err.println("Falha na conexão com a NUVEM");
                e.printStackTrace();
            }
        }
        driver.manage().window().setSize(new Dimension(1200, 765));
        return driver;
    }

    public static void killDriver(){
        WebDriver driver = getDriver();
        if(driver != null) {
            driver.quit();
            driver = null;
        }
        if(threadDriver != null) {
            threadDriver.remove();
        }
    }

}
