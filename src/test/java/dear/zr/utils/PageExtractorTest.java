package dear.zr.utils;

import dear.zr.domain.TextElement;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.jsoup.Jsoup.parse;
import static org.junit.Assert.assertThat;

public class PageExtractorTest {

    @Test
    public void no_text_node() throws Exception {
        List<TextElement> textNodes = PageExtractor.textElements(parse("<html></html>"));

        assertThat(textNodes.size(), is(0));
    }

    @Test
    public void has_one_text_node() throws Exception {
        List<TextElement> textNodes = PageExtractor.textElements(parse("<html><body>hello world</body></html>"));

        assertThat(textNodes.size(), is(1));
        assertThat(textNodes.get(0).getText(), is("hello world"));
        assertThat(textNodes.get(0).getCssSelector(), is("html > body"));
    }

    @Test
    public void has_two_text_node() throws Exception {
        List<TextElement> textNodes = PageExtractor.textElements(parse("<html><body>hello <a>world</a></body></html>"));

        assertThat(textNodes.size(), is(2));
        assertThat(textNodes.get(0).getText(), is("hello "));
        assertThat(textNodes.get(0).getCssSelector(), is("html > body"));
        assertThat(textNodes.get(1).getText(), is("world"));
        assertThat(textNodes.get(1).getCssSelector(), is("html > body > a"));
    }

    @Test
    public void has_three_text_node_and_two_in_the_same_level() throws Exception {
        List<TextElement> textNodes = PageExtractor.textElements(parse("<html><body>hello <a>world</a> nihao</body></html>"));

        assertThat(textNodes.size(), is(3));
        assertThat(textNodes.get(0).getText(), is("hello "));
        assertThat(textNodes.get(0).getCssSelector(), is("html > body"));
        assertThat(textNodes.get(1).getText(), is("world"));
        assertThat(textNodes.get(1).getCssSelector(), is("html > body > a"));
        assertThat(textNodes.get(2).getText(), is(" nihao"));
        assertThat(textNodes.get(2).getCssSelector(), is("html > body"));
    }

}