package dear.zr.page.utils;

public class MarkUtil {
    public static String mark(String text, String flag) {
        int start = text.indexOf(flag);
        int end = start + flag.length();
        return "\"" + text.substring(0, start) + "<span style='background-color:red'>" + text.substring(start, end) + "</span>" + text.substring(end) + "\"";
    }
}
