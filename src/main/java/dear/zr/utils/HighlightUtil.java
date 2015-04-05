package dear.zr.utils;

import dear.zr.domain.Range;
import dear.zr.domain.TextElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HighlightUtil {

    private static String ZR_TAG_NAME ="zr";
    private static String HIGHLIGHT_START_TAG ="<zr class='zr_node' style='background-color: red'>";
    private static String HIGHLIGHT_END_TAG ="</zr>";

    public static String insertHighlightTagToText(String text, int start, int end) {
        return text.substring(0, start) + HIGHLIGHT_START_TAG + text.substring(start, end) + HIGHLIGHT_END_TAG + text.substring(end);
    }

    public static String findNoteByCssSelectorAndChildNumJS(String cssSelector, int childNum) {
        return "document.querySelector(\'"+ cssSelector +"\').childNodes["+childNum+"];";
    }

    public static String findParentNodeJS(String element) {
        return element + ".parentNode;";
    }

    public static String replaceTexNodeWithElementNodeJS(String parentNode, int childNum, String newChild) {
        return parentNode+".replaceChild(" + newChild + ", parent.childNodes["+childNum+"]);";
    }

    public static String createElementJS(String tagName) {
        return "document.createElement(\""+ tagName+"\");";
    }

    public static String getElementNodeCreatorJS(String tag, String content) {
        return "function() {" +
                "var newElement = " + createElementJS(tag) +
                innerHTMLJS("newElement", content) +
                "return newElement" +
                "};";
    }

    public static String innerHTMLJS(String element, String data) {
        return element + ".innerHTML = \"" + data + "\";";
    }

    /**
     * text 后方 的range必须在 list的 前方
     */
    public static String getHighlightTextNodeContentFormText(String text, List<Range> rangeList) {
        String zrHighlightElementString = text;
        for (int i = rangeList.size()-1; i >= 0; i--) {
            zrHighlightElementString = insertHighlightTagToText(zrHighlightElementString, rangeList.get(i).getStartColumn(), rangeList.get(i).getEndColumn());
        }

        return zrHighlightElementString;
    }

    public static void highlight(WebDriver webDriver, TextElement textElement) {

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        String findTextNodeJs = "var textNode = " + findNoteByCssSelectorAndChildNumJS(textElement.getCssSelector(), textElement.getChildNum());

        String findTextNodeParentElementJS = "var parent = " + findParentNodeJS("textNode");

        String zrHighlightTextNoteJs = getHighlightTextNodeContentFormText(textElement.getText(), textElement.getWrongTextRangeList());

        String getZrHighlightElementCreatorJs = "var getZrHighlightElementFunction = " + getElementNodeCreatorJS(ZR_TAG_NAME, zrHighlightTextNoteJs);

        String createZrHighlightElementJs = "var newElement = getZrHighlightElementFunction();";

        String replaceTextNodeWithElementNodeJs = replaceTexNodeWithElementNodeJS("parent", textElement.getChildNum(), "newElement");

        String js = findTextNodeJs +
                findTextNodeParentElementJS +
                getZrHighlightElementCreatorJs +
                createZrHighlightElementJs +
                replaceTextNodeWithElementNodeJs;

        jse.executeScript(js);
    }
}
