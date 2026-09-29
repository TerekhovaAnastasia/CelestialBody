public abstract class CelestialBody implements Comparable<CelestialBody>{
    //астрономические объекты (небесные тела)
    protected final String name;
    //масса измеряется в массах Солнца - общий критерий сравнения
    protected final double mass;
    //в млрд лет
    protected final double age;
    // расстояние в световых годах (от наблюдателя, т.е. с Земли)
    protected final double distance;

    public CelestialBody(String name, double mass, double age, double distance){
        this.name = name;
        this.mass = mass;
        this.age = age;
        this.distance = distance;
    }

    public String getName() { return name; }
    public double getMass() { return mass; }
    public double getAge() { return age; }
    public double getDistance() { return distance; }

    @Override
    public int compareTo (CelestialBody other) {
        if (other == null) return 1;
        return Double.compare(this.mass, other.mass);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody that = (CelestialBody) o;
        return Double.compare(that.mass, mass) == 0
                && Double.compare(that.age, age) == 0
                && Double.compare(that.distance, distance) == 0
                && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, mass, age, distance);
    }

    @Override
    public String toString() {
        return String.format("%s[name=%s, mass=%.3g M☉, age=%.2f Gyr, distance=%.1f ly]",
                getClass().getSimpleName(), name, mass, age, distance);
    }
}
