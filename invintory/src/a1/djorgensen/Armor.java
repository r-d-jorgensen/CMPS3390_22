package a1.djorgensen;

public class Armor extends Item {
    private float defence;

    public Armor() {
        super();
        this.defence = 1;
    }

    public Armor(String name, double price, int qty, float defence) {
        super(name, price, qty);
        this.defence = defence;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = Math.max(defence, 0);
    }

    @Override
    public String toString() {
        return String.format("%s %6.2f |", super.toString(), this.defence);
    }
}
