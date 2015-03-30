package dear.zr.domain;

public class Range {

    private int startColumn;

    private int endColumn;

    public Range(int startColumn, int endColumn) {
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }
}
