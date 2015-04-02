package dear.zr.domain;

import org.jsoup.nodes.Element;

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
}
