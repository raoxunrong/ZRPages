package dear.zr.check;

import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.morfologik.MorfologikSpellerRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static dear.zr.utils.CollectionsUtil.intersection;

public class SpellChecker {

    private List<JLanguageTool> languageTools = new ArrayList<>();

    public SpellChecker addDict(Language language) throws IOException {

        JLanguageTool languageTool = new JLanguageTool(language);
        languageTool.activateDefaultPatternRules();
        languageTools.add(languageTool);

        return this;
    }

    public List<Range> check(String str) throws Exception {
        isLanguageToolInitialized();
        List<Set<Range>> wrongSpellingRangeList = new ArrayList<>();

        for (JLanguageTool jLanguageTool : languageTools) {

            Set<Range> wrongSpellingRanges = new HashSet();
            for (RuleMatch match : jLanguageTool.check(str)) {
                wrongSpellingRanges.add(new Range(match.getColumn() - 1, match.getEndColumn() - 1));
            }
            wrongSpellingRangeList.add(wrongSpellingRanges);
        }

        return newArrayList(intersection(wrongSpellingRangeList.toArray(new Set[0])));
    }

    private void isLanguageToolInitialized() throws Exception {
        if (languageTools.size() == 0) {
            throw new Exception("Can't find any initialized language");
        }
    }

    public SpellChecker addIgnoreWords(List<String> ignoreWords) throws Exception {
        isLanguageToolInitialized();
        JLanguageTool languageTool = languageTools.get(0);

        for (Rule rule : languageTool.getAllRules()) {
            if (rule.isDictionaryBasedSpellingRule()) {
                ((MorfologikSpellerRule) rule).addIgnoreTokens(ignoreWords);
            }
        }

        return this;
    }

}
