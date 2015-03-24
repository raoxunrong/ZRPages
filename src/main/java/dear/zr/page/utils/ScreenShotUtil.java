package dear.zr.page.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

    public static void take(WebDriver webDriver, String targetPath){
        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(targetPath));
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
