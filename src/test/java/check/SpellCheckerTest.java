package check;

import dear.zr.check.SpellChecker;
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
        System.out.println(errorWords.size());
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
        assertThat(errorWords.get(0).getStartColumn(), is(17));
        assertThat(errorWords.get(0).getEndColumn(), is(18));
        assertThat(errorWords.get(1).getStartColumn(), is(19));
        assertThat(errorWords.get(1).getEndColumn(), is(24));
    }

    @Test
    public void should_return_the_positions_of_error_words_using_multiple_languages() throws Exception {
        checker.addDict(new BritishEnglish())
               .addDict(new AmericanEnglish());

        String test = "A sentence with color and colour";   // 美式写法 color - colour 英式写法
        List<Range> errorWords = checker.check(test);

        System.out.println(errorWords.get(0).getStartColumn());
        System.out.println(errorWords.get(0).getEndColumn());
        System.out.println(errorWords.get(1).getStartColumn());
        System.out.println(errorWords.get(1).getEndColumn());
    }

}
