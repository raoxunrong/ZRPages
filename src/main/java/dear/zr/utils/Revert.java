package dear.zr.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Revert {


    public static String generateRevertJs(){
        String allZrNode = "var allZrNode = document.getElementsByClassName('zr_node');";
        String removeStyle = "for(var i=0;i<allZrNode.length;i++){allZrNode[i].removeAttribute('style')}";
        return allZrNode + removeStyle;
    }

    public static void revert(WebDriver webDriver){
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript(generateRevertJs());
    }
}
