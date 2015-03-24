package dear.zr.page.utils;

import dear.zr.page.WebDriverManager;
import dear.zr.page.utils.baidupages.BaiduHomePage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static dear.zr.page.utils.ScreenShotUtil.take;

public class ScreenShotUtilTest {

    private WebDriver webDriver;
    private BaiduHomePage baiduHomePage;

    @Before
    public void init() {
        webDriver = WebDriverManager.getWebDriver();
        baiduHomePage = WebDriverManager.goToPage("http://www.baidu.com", BaiduHomePage.class);
    }

    @Test
    public void should_save_the_screen_shot() throws InterruptedException {

        // Given
        webDriver = WebDriverManager.getWebDriver();
        baiduHomePage = WebDriverManager.goToPage("http://www.baidu.com", BaiduHomePage.class);

        // When
        take(webDriver, "/tmp/ZRPagesTmp/baidu_home.png");

        // Than
    }

    @AfterClass
    public static void destroy() {
        WebDriverManager.deleteWebDriver();
    }
}