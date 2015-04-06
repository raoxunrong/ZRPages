package dear.zr.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Revert {
    public static String zr_node = "zr_node";

    private static String generateRevertJs() {
        String allZrNode = "var allZrNode = document.getElementsByClassName('" + zr_node + "');";
        String removeStyle = "while(allZrNode.length!=0){" +
                "var text = allZrNode[allZrNode.length-1].innerHTML;    " +
                "var newTextNode = document.createTextNode(text);"+
                "allZrNode[allZrNode.length-1].parentNode.replaceChild(newTextNode,allZrNode[allZrNode.length-1])}";

        return allZrNode + removeStyle;
    }

    public static void revert(WebDriver webDriver) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript(generateRevertJs());
    }
}
