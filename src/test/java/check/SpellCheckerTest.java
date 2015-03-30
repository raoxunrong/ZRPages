package check;

import dear.zr.check.SpellChecker;
import dear.zr.domain.WordPosition;
import org.junit.Before;
import org.junit.Test;
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
        checker.initial();

        String test = "A sentence with a errer";
        List<WordPosition> errorWords = checker.check(test);
        assertThat(errorWords.size(), is(2));
    }

    @Test
    public void should_return_the_positions_of_error_words() throws Exception {
        checker.addDict(new BritishEnglish());
        checker.initial();

        String test = "A sentence with a errer";
        List<WordPosition> errorWords = checker.check(test);
        assertThat(errorWords.get(0).getStartColumn(), is(17));
        assertThat(errorWords.get(0).getEndColumn(), is(18));
        assertThat(errorWords.get(1).getStartColumn(), is(19));
        assertThat(errorWords.get(1).getEndColumn(), is(24));
    }

}
