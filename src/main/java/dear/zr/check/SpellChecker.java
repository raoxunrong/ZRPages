package dear.zr.check;

import dear.zr.domain.Range;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dear.zr.utils.ListUtil.findSameElem;

public class SpellChecker {

    private JLanguageTool languageTool;
    private List<JLanguageTool> languageTools = new ArrayList<>();

    public SpellChecker() {
    }

    public List<JLanguageTool> getLanguageTools() {
        return languageTools;
    }

    public SpellChecker addDict(Language language) throws IOException {

        languageTool = new JLanguageTool(language);
        languageTool.activateDefaultPatternRules();
        languageTools.add(languageTool);

        return this;
    }

    public List<Range> check(String str) throws IOException {

        List<List<Range>> rangeLists = new ArrayList<>();
        List<JLanguageTool> languageTools = this.getLanguageTools();

        for (JLanguageTool jLanguageTool : languageTools) {

            List<Range> ranges = new ArrayList<>();
            List<RuleMatch> matches = jLanguageTool.check(str);
            for (RuleMatch match : matches) {
                Range range = new Range(match.getColumn(), match.getEndColumn());
                ranges.add(range);
            }
            rangeLists.add(ranges);
        }

        List<Range> errorWords = findSameElem(rangeLists);

        return errorWords;
    }

}
