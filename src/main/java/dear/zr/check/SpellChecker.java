package dear.zr.check;

import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

    JLanguageTool languageTool;

    public SpellChecker() {

    }

    public SpellChecker addDict(Language language) throws IOException {
        if (languageTool == null) {
            languageTool = new JLanguageTool(language);
            languageTool.activateDefaultPatternRules();
        }

//        List<Rule> allRules = languageTool.getAllRules();
//        for (Rule rule : allRules) {
//            System.out.println("11111 : " + rule.getId());
//        }

        //Add new Rule
//        MorfologikAmericanSpellerRule americanSpellerRule = new MorfologikAmericanSpellerRule(JLanguageTool.getMessageBundle(), language);
//        languageTool.addRule(americanSpellerRule);

//        List<Rule> allRules2 = languageTool.getAllRules();
//        for (Rule rule : allRules2) {
//            System.out.println("22222 : " + rule.getId());
//        }

        return this;
    }

    public List<Range> check(String str) throws IOException {

        List<RuleMatch> matches = languageTool.check(str);
        List<Range> errorWords = new ArrayList<Range>();

        for (RuleMatch match : matches) {
            Range errorWord = new Range(match.getColumn(), match.getEndColumn());
            errorWords.add(errorWord);
        }

        return errorWords;
    }
}
