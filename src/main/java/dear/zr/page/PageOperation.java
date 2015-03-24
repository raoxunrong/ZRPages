package dear.zr.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageOperation {
    private static Page currentPage;

    public static <T extends Page> T on(Class<T> pageClass){
        WebDriver webDriver = WebDriverManager.getWebDriver();

        T page = PageFactory.initElements(webDriver, pageClass);
        currentPage = page;
        return page;
    }

    public static Page getCurrentPage(){
        return currentPage;
    }
}
