package dear.zr.utils;

import dear.zr.domain.Range;
import dear.zr.domain.TextElement;

import java.util.List;

public class HtmlContent {
    public static String prefixTag = "<span style='background-color:red'>";
    public static String postTag = "</span>";
    public static int tagLength = prefixTag.length() + postTag.length();


    public static String generateScript(TextElement textElement, List<Range> rangeList){
        return "document.querySelector(\"" + textElement.getCssSelector() + "\").innerHTML=" +
                addTag(textElement.getText(), rangeList);
    }

    public static String addTag(String text,List<Range> rangeList){
        String oldText = text;
        int count =0;
        for(Range range: rangeList){

            int adjustLength = count * tagLength;
            int startColumn = range.getStartColumn()+ adjustLength;
            int endColumn = range.getEndColumn() + adjustLength;

            oldText = oldText.substring(0, startColumn)+
                    prefixTag + oldText.substring(startColumn,endColumn)+
                    postTag + oldText.substring(endColumn);
            count = count + 1;
        }
        return oldText;
    }
}
