package dear.zr;

import dear.zr.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public final class ZR {

    public static <T extends Page> void on(Class<T> pageClass, WebDriver webDriver) {
        try {
            T page = pageClass.newInstance();
            webDriver.findElement(By.id(page.id()));
            //TODO need implementation
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {

        }
    }
}
