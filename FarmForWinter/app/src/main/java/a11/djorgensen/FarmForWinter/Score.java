package a11.djorgensen.FarmForWinter;

public class Score {
    private final String userName, value;

    public Score(String userName, String value) {
        this.value = value;
        this.userName = userName;
    }

    public String getValue() {
        return value;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() { return this.getUserName() + ": " + this.getValue(); }
}
