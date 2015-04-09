package dear.zr.utils;

/**
 * Created by xrrao on 4/9/15.
 */
public class JSGenerator {

    private StringBuffer codeSnippet = new StringBuffer();

    public JSGenerator define(String variableName) {
        append("var " + variableName + ";");
        return this;
    }

    public String generate() {
        return codeSnippet.toString();
    }

    public JSGenerator assign(String variableName, String value) {
        append(variableName + " = " + value + ";");
        return this;
    }

    private void append(String statement) {
        codeSnippet.append(((codeSnippet.length() == 0? "":"\n")) + statement);
    }

}
