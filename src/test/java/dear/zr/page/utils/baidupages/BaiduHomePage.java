package dear.zr.page.utils.baidupages;

import dear.zr.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaiduHomePage implements Page {

    private WebDriver webDriver;

    @FindBy(id = "su")
    private WebElement doSearchButton;

    @FindBy(id = "kw")
    private WebElement searchInput;

    @FindBy(name = "tj_trnews")
    private WebElement news;

    public BaiduHomePage(WebDriver driver) {
        this.webDriver = driver;
    }

    public String name() {
        return "Baidu hoe page";
    }

    public void doSearch(String keyWord){
        searchInput.sendKeys(keyWord);
        doSearchButton.click();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public WebElement getDoSearchButton() {
        return doSearchButton;
    }

    public void setDoSearchButton(WebElement doSearchButton) {
        this.doSearchButton = doSearchButton;
    }

    public WebElement getNews() {
        return news;
    }

    public void setNews(WebElement news) {
        this.news = news;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(WebElement searchInput) {
        this.searchInput = searchInput;
    }
}

