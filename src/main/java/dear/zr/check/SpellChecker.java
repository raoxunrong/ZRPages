package dear.zr.check;

import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.morfologik.MorfologikSpellerRule;

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

        MorfologikSpellerRule spellerRule = getSpellerRule(language);

        languageTool.addRule(spellerRule);

        return this;
    }

    private MorfologikSpellerRule getSpellerRule(final Language language) throws IOException {
        List<Rule> relevantRules = language.getRelevantRules(JLanguageTool.getMessageBundle());
        final Rule addRule = relevantRules.get(relevantRules.size() - 1);

        //Add new Rule
        return new MorfologikSpellerRule(JLanguageTool.getMessageBundle(), language) {
            @Override
            public String getFileName() {
                return ((MorfologikSpellerRule) addRule).getFileName();
            }

            @Override
            public String getId() {
                return addRule.getId();
            }
        };
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
