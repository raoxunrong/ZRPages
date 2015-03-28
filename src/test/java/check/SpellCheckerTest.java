package check;

import dear.zr.check.SpellChecker;
import dear.zr.domain.WordPosition;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SpellCheckerTest {

    SpellChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new SpellChecker();
        checker.initial();
    }

    @Test
    public void should_return_the_number_of_error_words() throws Exception {
        String test = "A sentence with a errer of the Hitchhiker's Guide to the Galaxy";
        List<WordPosition> errorWords = checker.check(test);
        assertThat(errorWords.size(), is(3));
    }

    @Test
    public void should_return_the_positions_of_error_words() throws Exception {
        String test = "A sentence with a errer";
        List<WordPosition> errorWords = checker.check(test);
        assertThat(errorWords.get(0).getStartColumn(), is(17));
        assertThat(errorWords.get(0).getEndColumn(), is(18));
        assertThat(errorWords.get(1).getStartColumn(), is(19));
        assertThat(errorWords.get(1).getEndColumn(), is(24));
    }
}
