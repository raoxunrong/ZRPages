package dear.zr.check;

import dear.zr.domain.Range;
import org.junit.Before;
import org.junit.Test;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SpellCheckerTest {

    SpellChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new SpellChecker();
    }

    @Test
    public void should_return_the_number_of_error_words() throws Exception {
        checker.addDict(new BritishEnglish());

        String test = "A sentence with a errer";
        List<Range> errorWords = checker.check(test);
        assertThat(errorWords.size(), is(2));
    }

    @Test
    public void should_return_null_number_of_error_words() throws Exception {
        checker.addDict(new BritishEnglish());

        String test = "A sentence with an error";
        List<Range> errorWords = checker.check(test);
        assertThat(errorWords.size(), is(0));
    }

    @Test
    public void should_return_the_positions_of_error_words_using_one_language() throws Exception {
        checker.addDict(new BritishEnglish());

        String test = "A sentence with a errer";
        List<Range> errorWords = checker.check(test);
        assertThat(errorWords.get(0).getStartColumn(), is(16));
        assertThat(errorWords.get(0).getEndColumn(), is(17));
        assertThat(errorWords.get(1).getStartColumn(), is(18));
        assertThat(errorWords.get(1).getEndColumn(), is(23));
        assertThat(test.substring(errorWords.get(0).getStartColumn(), errorWords.get(0).getEndColumn()), is("a"));
        assertThat(test.substring(errorWords.get(1).getStartColumn(), errorWords.get(1).getEndColumn()), is("errer"));
    }

    @Test
    public void should_remove_duplicate_error_words_using_2_kinds_of_languages() throws Exception {
        checker.addDict(new BritishEnglish())
               .addDict(new AmericanEnglish());

        String test = "A sentence with tehv";
        List<Range> errorWords = checker.check(test);

        assertThat(errorWords.size(), is(1));
        assertThat(errorWords.get(0).getStartColumn(), is(16));
        assertThat(errorWords.get(0).getEndColumn(), is(20));
        assertThat(test.substring(errorWords.get(0).getStartColumn(), errorWords.get(0).getEndColumn()), is("tehv"));
    }

    @Test
    public void should_return_the_error_words_using_multiple_languages() throws Exception {
        checker.addDict(new BritishEnglish())
               .addDict(new AmericanEnglish());

        String test = "A sentence with color and colour";   // 美式写法 color - colour 英式写法
        List<Range> errorWords = checker.check(test);

        assertThat(errorWords.size(), is(0));
    }

    @Test
    public void should_check_when_add_additional_ignore_words() throws Exception {

        checker.addDict(new BritishEnglish())
               .addIgnoreWords("errorTwo", "errorThree");

        String test = "Words errorOne, errorTwo and errorThree";
        List<Range> errorWords = checker.check(test);

        assertThat(errorWords.size(), is(1));
        assertThat(errorWords.get(0).getStartColumn(), is(6));
        assertThat(errorWords.get(0).getEndColumn(), is(14));
        assertThat(test.substring(errorWords.get(0).getStartColumn(), errorWords.get(0).getEndColumn()), is("errorOne"));
    }

    @Test
    public void should_check_with_special_symbols() throws Exception {

        checker.addDict(new BritishEnglish());

        String test = "www/baidu,come0980**common*()!hello.";

        List<Range> errorWords = checker.check(test);

        assertThat(errorWords.size(), is(2));
        assertThat(test.substring(errorWords.get(0).getStartColumn(), errorWords.get(0).getEndColumn()), is("www"));
        assertThat(test.substring(errorWords.get(1).getStartColumn(), errorWords.get(1).getEndColumn()), is("baidu"));
    }

}
