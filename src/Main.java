import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<CelestialBody> bodies = readBodiesFromFile("input.txt");
        // исходный список
        //printSeparator - метод в самом конце Main
        printSeparator("исходный список");
        bodies.forEach(System.out::println);

        //сортировка через Comparable (по массе)
        Collections.sort(bodies);
        printSeparator("сортировка по массе (возрастание)");
        for (int i = 0; i < bodies.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, bodies.get(i));
        }

        //проверка equals ВНИМАНИЕ!! в объекте с параметр luminosity = 2.0, а не 1.0
        printSeparator("проверка equals");
        CelestialBody a = new Star("Sun", 1.0, 4.6, 0.000016, 696340, 1.0, "G2V");
        CelestialBody b = new Star("Sun", 1.0, 4.6, 0.000016, 696340, 1.0, "G2V");
        CelestialBody c = new Star("Sun", 1.0, 4.6, 0.000016, 696340, 2.0, "G2V");
        System.out.println("a.equals(b) = " + a.equals(b) + "  (ожидаем true)");
        System.out.println("a.equals(c) = " + a.equals(c) + "  (ожидаем false)");

        printSeparator("звезды массой больше 5 M☉");
        bodies.stream()
                .filter(x -> x instanceof Star && x.getMass() > 5.0)
                .forEach(System.out::println);

        printSeparator("планеты с атмосферой");
        bodies.stream()
                .filter(x -> x instanceof Planet p && p.isHasAtmosphere())
                .forEach(System.out::println);

        printSeparator("черные дыры массой > 1e6 M☉");
        bodies.stream()
                .filter(x -> x instanceof BlackHole && x.getMass() > 1e6)
                .forEach(System.out::println);

        printSeparator("классификация по типу");
        Map<String, Long> stats = bodies.stream()
                .collect(Collectors.groupingBy(
                        x -> x.getClass().getSimpleName(),
                        TreeMap::new,
                        Collectors.counting()));
        stats.forEach((k, v) -> System.out.println(k + ": " + v));

        printSeparator("классификация по спектральному классу");
        bodies.stream()
                .filter(x -> x instanceof Star)
                .map(x -> (Star) x)
                .collect(Collectors.groupingBy(
                        s -> s.getSpectralClass().substring(0, 1),
                        TreeMap::new,
                        Collectors.counting()))
                .forEach((k, v) -> System.out.println("Класс " + k + ": " + v));

        printSeparator("радиусы шварцшильда у черных дыр");
        bodies.stream()
                .filter(x -> x instanceof BlackHole)
                .map(x -> (BlackHole) x)
                .forEach(bh -> System.out.printf("%-16s r_s = %.3g км%n",
                        bh.getName(), bh.getSchwarzschildRadius()));
    }

    private static List<CelestialBody> readBodiesFromFile(String fileName) throws IOException {
        List<CelestialBody> bodies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] p = line.split(";");
                if (p.length < 5) {
                    System.err.println("Строка " + lineNumber + ": мало полей, пропускаю");
                    continue;
                }

                String type     = p[0].trim().toLowerCase();
                String name     = p[1].trim();
                double mass     = Double.parseDouble(p[2].trim());
                double age      = Double.parseDouble(p[3].trim());
                double distance = Double.parseDouble(p[4].trim());

                try {
                    switch (type) {
                        case "star" -> {
                            double radius      = Double.parseDouble(p[5].trim());
                            double luminosity  = Double.parseDouble(p[6].trim());
                            String spectralCls = p[7].trim();
                            bodies.add(new Star(name, mass, age, distance,
                                    radius, luminosity, spectralCls));
                        }
                        case "planet" -> {
                            double radius         = Double.parseDouble(p[5].trim());
                            double orbitalPeriod  = Double.parseDouble(p[6].trim());
                            boolean hasAtmosphere = Boolean.parseBoolean(p[7].trim());
                            bodies.add(new Planet(name, mass, age, distance,
                                    radius, orbitalPeriod, hasAtmosphere));
                        }
                        case "blackhole" -> {
                            double spin   = Double.parseDouble(p[5].trim());
                            double charge = Double.parseDouble(p[6].trim());
                            bodies.add(new BlackHole(name, mass, age, distance,
                                    spin, charge));
                        }
                        default -> System.err.println(
                                "Строка " + lineNumber + ": неизвестный тип '" + type + "'");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.err.println("Строка " + lineNumber + ": не хватает полей для '" + type + "'");
                } catch (NumberFormatException ex) {
                    System.err.println("Строка " + lineNumber + ": не удалось разобрать число");
                }
            }
        }
        return bodies;
    }

    //чтоб красиво было
    private static void printSeparator(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  " + title);
        System.out.println("=".repeat(70));
    }
}