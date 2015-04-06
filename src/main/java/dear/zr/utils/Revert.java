package dear.zr.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Revert {
    public static String zrTag = "zr";

    private static String generateJsForRevert() {
        String allZrNode = "var allZrNode = document.getElementsByTagName('" + zrTag + "');";
        String remove = "while(allZrNode.length!=0){" +
                "var text = allZrNode[allZrNode.length-1].innerHTML;" +
                "var newTextNode = document.createTextNode(text);"+
                "allZrNode[allZrNode.length-1].parentNode.replaceChild(newTextNode,allZrNode[allZrNode.length-1])}";

        return allZrNode + remove;
    }

    public static void revert(WebDriver webDriver) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript(generateJsForRevert());
    }
}
