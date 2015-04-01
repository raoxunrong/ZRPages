package dear.zr.check;

import com.google.common.collect.ImmutableSet;
import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.Rule;
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

        Rule spellerRule = getSpellerRule(language);
        languageTool.addRule(spellerRule);

        return this;
    }

    private Rule getSpellerRule(final Language language) throws IOException {
        //Gets the all rules for the language.
        List<Rule> relevantRules = language.getRelevantRules(JLanguageTool.getMessageBundle());
        //The last rule is about this language speller rule.
        Rule spellerRule = relevantRules.get(relevantRules.size() - 1);

        return spellerRule;
    }

    public List<Range> check(String str) throws IOException {

        List<RuleMatch> matches = languageTool.check(str);
        List<Range> ranges = new ArrayList<Range>();

        for (RuleMatch match : matches) {
            Range range = new Range(match.getColumn(), match.getEndColumn());
            ranges.add(range);
        }

        ImmutableSet<Range> errorWords = ImmutableSet.copyOf(ranges);

        return errorWords.asList();
    }
}
