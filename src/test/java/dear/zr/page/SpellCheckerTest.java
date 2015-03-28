package dear.zr.page;

import dear.zr.SpellChecker;
import org.junit.Before;
import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;

public class SpellCheckerTest {

    SpellChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new SpellChecker(new JLanguageTool(new BritishEnglish()));
    }

    @Test
    public void spellChecker() throws Exception {
        String test = "A sentence with a error of the Hitchhiker's Guide tot he Galaxy";
        checker.check(test);
    }
}
