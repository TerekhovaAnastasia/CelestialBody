public class Planet extends CelestialBody {
    // радиус поверхности (км)
    private final double radius;
    // года
    private final double orbitalPeriod;
    private final boolean hasAtmosphere;

    public Planet(String name, double mass, double age, double distance,
                  double radius, double orbitalPeriod, boolean hasAtmosphere) {
        super(name, mass, age, distance);
        this.radius = radius;
        this.orbitalPeriod = orbitalPeriod;
        this.hasAtmosphere = hasAtmosphere;
    }

    public double getRadius() { return radius; }
    public double getOrbitalPeriod() { return orbitalPeriod; }
    public boolean isHasAtmosphere() { return hasAtmosphere; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.radius, radius) == 0
                && Double.compare(planet.orbitalPeriod, orbitalPeriod) == 0
                && hasAtmosphere == planet.hasAtmosphere;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), radius, orbitalPeriod, hasAtmosphere);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " {R=%.3g km, T_orbit=%.2f yr, atmosphere=%s}",
                radius, orbitalPeriod, hasAtmosphere);
    }
}
