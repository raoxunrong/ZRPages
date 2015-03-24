package dear.zr.page.utils;

import dear.zr.page.WebDriverManager;
import dear.zr.page.utils.baidupages.BaiduHomePage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static dear.zr.page.utils.MarkUtil.mark;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MarkAndScreenShotTest {

    private WebDriver webDriver;
    private  BaiduHomePage baiduHomePage;

    @Before
    public void init() {
        webDriver = WebDriverManager.getWebDriver();
        baiduHomePage = WebDriverManager.goToPage("http://www.baidu.com", BaiduHomePage.class);
    }

    @Test
    public void should_do_search() {
        // Given

        // When
        baiduHomePage.doSearch("cuit");
        waitForReady(webDriver, "cuit");

        // Than
        assertThat(webDriver.getTitle().startsWith("cuit"), is(true));
    }

    @Test
    public void should_mark_target_word_when_classed_element_contains_the_word() throws InterruptedException {
        // Given
        waitForReady(webDriver, "百度");
        baiduHomePage.doSearch("cuit");
        waitForReady(webDriver,"cuit");

        // When
        WebElement webElement = webDriver.findElement(By.xpath("//*[contains(text(), '百度首页')]"));
        String _class = "." + webElement.getAttribute("class");
        String js = "$('"+_class+"')[0].innerHTML = "+ mark(webElement.getText(), "百度首页");
        ((JavascriptExecutor) webDriver).executeScript(js);

        // Than
    }

    @Test
    public void should_mark_target_word_when_element_text_contains_the_word() throws InterruptedException {
        // Given
        waitForReady(webDriver, "百度");
        baiduHomePage.doSearch("cuit");
        waitForReady(webDriver, "cuit");

        // When
        WebElement webElement = webDriver.findElement(By.xpath("//*[contains(text(), '行政楼')]"));
        String js = "document.getElementsByClassName(\"c-abstract\")[0].innerHTML = " + mark(webElement.getText(), "行政楼");
        ((JavascriptExecutor)webDriver).executeScript(js);

        // Than
    }

    @AfterClass
    public static void destroy() {
        WebDriverManager.deleteWebDriver();
    }

    private void waitForReady(WebDriver webDriver, final String startWith) {
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().startsWith(startWith);
            }
        });
    }
}



