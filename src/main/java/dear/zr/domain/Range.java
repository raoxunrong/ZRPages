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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;

        Range range = (Range) o;

        if (endColumn != range.endColumn) return false;
        if (startColumn != range.startColumn) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startColumn;
        result = 31 * result + endColumn;
        return result;
    }
}
