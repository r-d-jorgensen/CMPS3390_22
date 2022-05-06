package a11.djorgensen.FarmForWinter;

public class Score {
    private final String value;

    public Score(String value) { this.value = value; }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() { return this.getValue(); }
}
