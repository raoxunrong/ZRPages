package dear.zr.utils;

import org.junit.Test;

import static dear.zr.utils.TextChange.insertAGroupOfTagToText;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TextChangeTest {


    private static String startTag = "<zr style='background-color:red'>";
    private static String endTag = "</zr>";

    @Test
    public void should_insert_hight_tag() {
        String text = "This is worng wording test.";

        String textContainsHighlightTag = insertAGroupOfTagToText(text, 8, 13, startTag, endTag);

        assertThat(textContainsHighlightTag.contains(startTag), is(true));
        assertThat(textContainsHighlightTag.contains(endTag), is(true));
        assertThat(textContainsHighlightTag, is("This is <zr style='background-color:red'>worng</zr> wording test."));
    }
}