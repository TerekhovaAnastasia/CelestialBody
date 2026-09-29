public class BlackHole extends CelestialBody {
    // спин 0<=a*<=1
    private final double spin;
    // заряд 0<=Q<=1
    private final double charge;

    public BlackHole(String name, double mass, double age, double distance,
                     double spin, double charge) {
        super(name, mass, age, distance);
        this.spin = spin;
        this.charge = charge;
    }

    public double getSpin() { return spin; }
    public double getCharge() { return charge; }

    // радиус Шварцшильда
    public double getSchwarzschildRadius() {
        return 2.95 * mass;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        BlackHole bh = (BlackHole) o;
        return Double.compare(bh.spin, spin) == 0
                && Double.compare(bh.charge, charge) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), spin, charge);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " {r_s=%.3g km, a*=%.2f, Q=%.2f}",
                getSchwarzschildRadius(), spin, charge);
    }
}
