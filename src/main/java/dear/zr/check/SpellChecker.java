package dear.zr.check;

import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dear.zr.utils.CollectionsUtil.intersection;

public class SpellChecker {

    private List<JLanguageTool> languageTools = new ArrayList<>();

    public SpellChecker addDict(Language language) throws IOException {

        JLanguageTool languageTool = new JLanguageTool(language);
        languageTool.activateDefaultPatternRules();
        languageTools.add(languageTool);

        return this;
    }

    public List<Range> check(String str) throws IOException {

        List<Set<Range>> wrongSpellingRangeList = new ArrayList<>();

        for (JLanguageTool jLanguageTool : languageTools) {

            Set<Range> wrongSpellingRanges = new HashSet();
            for (RuleMatch match : jLanguageTool.check(str)) {
                wrongSpellingRanges.add(new Range(match.getColumn(), match.getEndColumn()));
            }
            wrongSpellingRangeList.add(wrongSpellingRanges);
        }

        return new ArrayList(intersection(wrongSpellingRangeList.toArray(new Set[0])));
    }

}
