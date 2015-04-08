package dear.zr.domain;

import org.jsoup.nodes.Element;

import java.util.List;

public class TextElement {
    private final String cssSelector;
    private final String text;

    public TextElement(String text, Element parent) {
        this.text = text;
        this.cssSelector = parent.cssSelector();
    }

    public String getText() {
        return text;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public int getChildNum() {
        return 0;
    }

    public List<Range> getWrongTextRangeList() {
        return null;
    }
}
