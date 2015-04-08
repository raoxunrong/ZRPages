package dear.zr.utils;

import dear.zr.domain.Range;
import dear.zr.domain.TextElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HighlightUtil {

    public static String ZR_TAG_NAME ="zr";
    public static String HIGHLIGHT_PREFIX_TAG ="<zr class='zr_node' style='background-color: red'>";
    public static String HIGHLIGHT_POST_TAG ="</zr>";

    public static void highlight(WebDriver webDriver, TextElement textElement) {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;

        String findTextNodeJS = "var textNode = " + findNodeJS(textElement.getCssSelector(), textElement.getChildNum());

        String findTextNodeParentJS = "var parent = " + getParentJS("textNode");

        String zrElementContent = zrElementContent(textElement.getText(), textElement.getWrongTextRangeList());

        String getZrHighlightElementCreatorJS = "var zrHighlightElementCreator = " + getElementCreatorJS(ZR_TAG_NAME, zrElementContent);

        String createZrHighlightElementJS = "var zrHighlightElement = zrHighlightElementCreator();";

        String replaceTextNodeWithZrHighlightElementJS = replaceChildJS("parent", textElement.getChildNum(), "zrHighlightElement");

        String js = findTextNodeJS +
                findTextNodeParentJS +
                getZrHighlightElementCreatorJS +
                createZrHighlightElementJS +
                replaceTextNodeWithZrHighlightElementJS;

        jsExecutor.executeScript(js);
    }

    /**
     * 必须先处理位置靠后的wrong range
     */
    private static String zrElementContent(String text, List<Range> rangeList) {
        String zrElementContent = text;

        for (int i = rangeList.size()-1; i >= 0; i--) {
            zrElementContent = insertHighlightTagToText(zrElementContent, rangeList.get(i).getStartColumn(), rangeList.get(i).getEndColumn());
        }

        return zrElementContent;
    }

    private static String insertHighlightTagToText(String text, int prefixSeat, int postSeat) {
        return text.substring(0, prefixSeat) + HIGHLIGHT_PREFIX_TAG + text.substring(prefixSeat, postSeat) + HIGHLIGHT_POST_TAG + text.substring(postSeat);
    }

    private static String findNodeJS(String cssSelector, int childNum) {
        return "document.querySelector(\'"+ cssSelector +"\').childNodes["+childNum+"];";
    }

    private static String getParentJS(String element) {
        return element + ".parentNode;";
    }

    private static String replaceChildJS(String parent, int childNum, String newChild) {
        return parent+".replaceChild(" + newChild + ", parent.childNodes["+childNum+"]);";
    }

    private static String createElementJS(String tagName) {
        return "document.createElement(\""+ tagName+"\");";
    }

    private static String getElementCreatorJS(String tag, String content) {
        return "function() {" +
                "var newElement = " + createElementJS(tag) +
                innerHTMLJS("newElement", content) +
                "return newElement" +
                "};";
    }

    private static String innerHTMLJS(String element, String data) {
        return element + ".innerHTML = \"" + data + "\";";
    }
}
