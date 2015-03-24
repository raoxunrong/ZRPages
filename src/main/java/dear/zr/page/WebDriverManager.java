package dear.zr.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static dear.zr.page.PageOperation.on;

public class WebDriverManager {

    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null){
            webDriver = new FirefoxDriver();
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        return webDriver;
    }

    public static void deleteWebDriver(){
        if(webDriver != null){
            webDriver.close();
            webDriver = null;
        }
    }

    public static <T extends Page> T goToPage(String subURL, Class<T> pageClass){
        webDriver.navigate().to(subURL);
        return on(pageClass);
    }
}
