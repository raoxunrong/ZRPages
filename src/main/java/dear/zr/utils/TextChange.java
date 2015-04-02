package dear.zr.utils;

public class TextChange {

    public static String insertAGroupOfTagToText(String text, int start, int end, String startTag, String endTag) {
        return text.substring(0, start) + startTag + text.substring(start, end) + endTag + text.substring(end);
    }
}
