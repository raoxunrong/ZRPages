package dear.zr.check;

import dear.zr.domain.WordPosition;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

    JLanguageTool languageTool;

    public SpellChecker() {
        languageTool = new JLanguageTool(new BritishEnglish());
    }

    public void initial() throws IOException {
        languageTool.activateDefaultPatternRules();
    }

    public List<WordPosition> check(String str) throws IOException {

        List<RuleMatch> matches = languageTool.check(str);
        List<WordPosition> errorWords = new ArrayList<WordPosition>();

        for (RuleMatch match : matches) {
            WordPosition errorWord = new WordPosition(match.getColumn(), match.getEndColumn());
            errorWords.add(errorWord);
        }

        return errorWords;
    }
}
