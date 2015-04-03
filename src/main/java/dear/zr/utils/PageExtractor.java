package dear.zr.utils;

import dear.zr.domain.TextElement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import java.util.LinkedList;
import java.util.List;

public class PageExtractor {

    public static List<TextElement> textElements(Document document) {
        final List<TextElement> textElements = new LinkedList<>();

        new NodeTraversor(new NodeVisitor() {
            public void head(Node node, int depth) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    if (!textNode.isBlank()) {
                        textElements.add(new TextElement(textNode.getWholeText(), (Element) textNode.parent()));
                    }
                }
            }

            public void tail(Node node, int depth) {
            }
        }).traverse(document.body());

        return textElements;
    }
}
