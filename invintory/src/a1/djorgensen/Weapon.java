package a1.djorgensen;

public class Weapon extends Item{
    private float damage;

    public Weapon() {
        this.damage = 1;
    }

    public Weapon(String name, double price, int qty, float damage) {
        super(name, price, qty);
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return String.format("%s %6.2f |", super.toString(), this.damage);
    }
}
