package dear.zr.utils;

import dear.zr.domain.Range;
import dear.zr.domain.TextElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static dear.zr.utils.HighlightUtil.highlight;
import static java.lang.System.exit;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HighlightUtilTest {

    private WebDriver webDriver;
    private String zrTagName;
    private String zrHighlightTagName;

    private String testHtmlPath = "/home/habens/Workspaces/ThoughtWorks/Objects/ZRPages/src/test/resources/for_highlight_test.html";

    @Before
    public void setUp() {
        try {
            zrTagName="zr";
            zrHighlightTagName="zr";

            webDriver = new FirefoxDriver();

            File testHtml = new File(testHtmlPath);
            if (!testHtml.exists()) {
                System.out.println("maybe you need to change path of for_highlight_test.html");
                webDriver.close();
                exit(0);
            }

            webDriver.get("file://" + testHtmlPath);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("maybe you need to install Firefox");
            webDriver.close();
            exit(0);
        }
    }

    @Test
    public void should_correct_highlight_word_when_textNode_has_multi_wrong_words() {

        TextElement textElement = mock(TextElement.class);
        when(textElement.getText()).thenReturn("Tihs is worng ");
        when(textElement.getCssSelector()).thenReturn("html>body>div");
        when(textElement.getChildNum()).thenReturn(0);
        List<Range> rangeList = new ArrayList<>();
        rangeList.add(new Range(0, 4));
        rangeList.add(new Range(8, 13));
        when(textElement.getWrongTextRangeList()).thenReturn(rangeList);

        highlight(webDriver, textElement);
        List<WebElement> elementList = webDriver.findElements(By.cssSelector(textElement.getCssSelector() + ">" + zrTagName + ">" + zrHighlightTagName));

        assertThat(elementList.size(), is(2));
        assertThat(elementList.get(0).getText(), is("Tihs"));
        assertThat(elementList.get(1).getText(), is("worng"));
    }

    @Test
    public void should_correct_highlight_word_when_textNode_has_child_note() {

        TextElement textElement = mock(TextElement.class);
        when(textElement.getText()).thenReturn("This is worng ");
        when(textElement.getCssSelector()).thenReturn("html>body>div");
        when(textElement.getChildNum()).thenReturn(0);
        List<Range> rangeList = new ArrayList<>();
        rangeList.add(new Range(8, 13));
        when(textElement.getWrongTextRangeList()).thenReturn(rangeList);

        highlight(webDriver, textElement);

        TextElement textElement2 = mock(TextElement.class);
        when(textElement2.getText()).thenReturn("for tset.");
        when(textElement2.getCssSelector()).thenReturn("html>body>div");
        when(textElement2.getChildNum()).thenReturn(2);
        List<Range> rangeList2 = new ArrayList<>();
        rangeList2.add(new Range(4, 8));
        when(textElement2.getWrongTextRangeList()).thenReturn(rangeList2);

        highlight(webDriver, textElement2);


        TextElement textElement3 = mock(TextElement.class);
        when(textElement3.getText()).thenReturn("wordnig ");
        when(textElement3.getCssSelector()).thenReturn("html>body>div>a");
        when(textElement3.getChildNum()).thenReturn(0);
        List<Range> rangeList3 = new ArrayList<>();
        rangeList3.add(new Range(0, 7));
        when(textElement3.getWrongTextRangeList()).thenReturn(rangeList3);

        highlight(webDriver, textElement3);


        List<WebElement> elementList = webDriver.findElements(By.cssSelector(textElement.getCssSelector() + ">" + zrTagName + ">" + zrHighlightTagName));

        assertThat(elementList.size(), is(2));
        assertThat(elementList.get(0).getText(), is("worng"));
        assertThat(elementList.get(1).getText(), is("tset"));

        List<WebElement> elementList2 = webDriver.findElements(By.cssSelector(textElement3.getCssSelector() + ">" + zrTagName + ">" + zrHighlightTagName));

        assertThat(elementList2.size(), is(1));
        assertThat(elementList2.get(0).getText(), is("wordnig"));
    }

    @After
    public void destroy() {
        webDriver.close();
    }
}