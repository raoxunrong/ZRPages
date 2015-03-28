package dear.zr;

import dear.zr.page.WordPosition;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class SpellChecker {

    JLanguageTool languageTool;

    public SpellChecker(JLanguageTool languageTool) {
        this.languageTool = languageTool;
    }

    public void initial() throws IOException {
        languageTool = new JLanguageTool(new BritishEnglish());
        languageTool.activateDefaultPatternRules();
    }

    public List<WordPosition> check(String str) throws IOException {

        List<RuleMatch> matches = languageTool.check(str);

        for (RuleMatch match : matches) {
            System.out.println("Potential error at line " + match.getLine()
                    + ", column " + match.getColumn() + "-" + match.getEndColumn());
            System.out.println("Suggested correction: " +
                    match.getSuggestedReplacements() + "\n");
        }

        return null;
    }
}
