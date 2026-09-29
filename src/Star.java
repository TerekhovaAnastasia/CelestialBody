public class Star extends CelestialBody{
    //радиус фотосферы (км)
    private final double radius;
    //светимость (эталон - светимость солнца, как единица измерения)
    private final double luminosity;
    private final String spectralClass; //O, B, A, F, G, K, M

    public Star(String name, double mass, double age, double distance,
                double radius, double luminosity, String spectralClass){
        super(name,mass,age,distance);
        this.radius = radius;
        this.luminosity = luminosity;
        this.spectralClass = spectralClass;
    }

    public double getRadius() { return radius; }
    public double getLuminosity() { return luminosity; }
    public String getSpectralClass() { return spectralClass; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Star star = (Star) o;
        return Double.compare(star.radius, radius) == 0
                && Double.compare(star.luminosity, luminosity) == 0
                && spectralClass.equals(star.spectralClass);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), radius, luminosity, spectralClass);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " {R=%.3g km, L=%.3g L☉, class=%s}",
                radius, luminosity, spectralClass);
    }
}
