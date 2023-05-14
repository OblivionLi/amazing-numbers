package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Properties {
    BUZZ("BUZZ", "-BUZZ"),
    DUCK("DUCK", "-DUCK"),
    PALINDROMIC("PALINDROMIC", "-PALINDROMIC"),
    GAPFUL("GAPFUL", "-GAPFUL"),
    SPY("SPY", "-SPY"),
    EVEN("EVEN", "-EVEN"),
    ODD("ODD", "-ODD"),
    SQUARE("SQUARE", "-SQUARE"),
    SUNNY("SUNNY", "-SUNNY"),
    JUMPING("JUMPING", "-JUMPING"),
    HAPPY("HAPPY", "-HAPPY"),
    SAD("SAD", "-SAD");

    private final String original;
    private final String prefixed;

    Properties(String original, String prefixed) {
        this.original = original;
        this.prefixed = prefixed;
    }

    public String getOriginal() {
        return this.original;
    }

    public String getPrefixed() {
        return this.prefixed;
    }

    private static boolean isValidProperty(String property) {
        for (Properties p : Properties.values()) {
            if (p.original.equalsIgnoreCase(property) || p.prefixed.equalsIgnoreCase(property)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getInvalidProperties(Set<String> properties) {
        if (properties.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> invalidProperties = new ArrayList<>();
        for (String property : properties) {
            boolean isPropertyValid = Properties.isValidProperty(property);

            if (!isPropertyValid) {
                invalidProperties.add(property);
            }
        }

        return invalidProperties;
    }

    public static boolean hasConflicts(Set<String> curatedProperties) {
        if (Properties.hasBothOddAndEven(curatedProperties) ||
                Properties.hasBothDuckAndSpy(curatedProperties) ||
                Properties.hasBothSunnyAndSquare(curatedProperties) ||
                Properties.hasBothHappyAndSad(curatedProperties) ||
                Properties.hasConflictingNegations(curatedProperties)
        ) {
            System.out.println("There are no numbers with these properties.");
            return true;
        }

        return false;
    }

    private static boolean hasBothOddAndEven(Set<String> properties) {
        if ((properties.contains(Properties.ODD.getOriginal()) &&
                properties.contains(Properties.EVEN.getOriginal())) ||
                (properties.contains(Properties.ODD.getPrefixed()) &&
                    properties.contains(Properties.EVEN.getPrefixed()))
        ) {
            String oddProperty = properties.contains(Properties.ODD.getPrefixed()) ? Properties.ODD.getPrefixed() : Properties.ODD.getOriginal();
            String evenProperty = properties.contains(Properties.EVEN.getPrefixed()) ? Properties.EVEN.getPrefixed() : Properties.EVEN.getOriginal();

            System.out.println(
                "The request contains mutually exclusive properties: [" +
                    oddProperty +
                    ", " +
                    evenProperty +
                "]."
            );
            return true;
        }

        return false;
    }

    private static boolean hasBothDuckAndSpy(Set<String> properties) {
        if ((properties.contains(Properties.DUCK.getOriginal()) &&
                properties.contains(Properties.SPY.getOriginal())) ||
                (properties.contains(Properties.DUCK.getPrefixed()) &&
                    properties.contains(Properties.SPY.getPrefixed()))
        ) {
            String duckProperty = properties.contains(Properties.DUCK.getPrefixed()) ? Properties.DUCK.getPrefixed() : Properties.DUCK.getOriginal();
            String spyProperty = properties.contains(Properties.SPY.getPrefixed()) ? Properties.SPY.getPrefixed() : Properties.SPY.getOriginal();

            System.out.println(
                "The request contains mutually exclusive properties: [" +
                    duckProperty +
                    ", " +
                    spyProperty +
                "]."
            );
            return true;
        }

        return false;
    }

    private static boolean hasBothSunnyAndSquare(Set<String> properties) {
        if ((properties.contains(Properties.SUNNY.getOriginal()) &&
                properties.contains(Properties.SQUARE.getOriginal())) ||
                (properties.contains(Properties.SUNNY.getPrefixed()) &&
                    properties.contains(Properties.SQUARE.getPrefixed()))
        ) {
            String sunnyProperty = properties.contains(Properties.SUNNY.getPrefixed()) ? Properties.SUNNY.getPrefixed() : Properties.SUNNY.getOriginal();
            String squareProperty = properties.contains(Properties.SQUARE.getPrefixed()) ? Properties.SQUARE.getPrefixed() : Properties.SQUARE.getOriginal();

            System.out.println(
                "The request contains mutually exclusive properties: [" +
                    sunnyProperty +
                    ", " +
                    squareProperty +
                "]."
            );
            return true;
        }

        return false;
    }

    private static boolean hasBothHappyAndSad(Set<String> properties) {
        if ((properties.contains(Properties.HAPPY.getOriginal()) &&
                properties.contains(Properties.SAD.getOriginal())) ||
                (properties.contains(Properties.HAPPY.getPrefixed()) &&
                    properties.contains(Properties.SAD.getPrefixed()))
        ) {
            String happyProperty = properties.contains(Properties.HAPPY.getPrefixed()) ? Properties.HAPPY.getPrefixed() : Properties.HAPPY.getOriginal();
            String sadProperty = properties.contains(Properties.SAD.getPrefixed()) ? Properties.SAD.getPrefixed() : Properties.SAD.getOriginal();

            System.out.println(
                "The request contains mutually exclusive properties: [" +
                    happyProperty +
                    ", " +
                    sadProperty +
                "]."
            );
            return true;
        }

        return false;
    }

    public static boolean hasConflictingNegations(Set<String> properties) {
        List<String> propertyList = new ArrayList<>(properties);

        for (int i = 0; i < propertyList.size(); i++) {
            String property = propertyList.get(i);

            if (property.startsWith("-")) {
                String positiveProperty = property.substring(1);
                if (propertyList.contains(positiveProperty)) {
                    System.out.println("The request contains mutually exclusive properties: [" +
                            property + ", " + positiveProperty + "].");
                    return true;
                }
            } else {
                String negativeProperty = "-" + property;
                if (propertyList.contains(negativeProperty)) {
                    System.out.println("The request contains mutually exclusive properties: [" +
                            property + ", " + negativeProperty + "].");
                    return true;
                }
            }
        }

        return false;
    }
}
