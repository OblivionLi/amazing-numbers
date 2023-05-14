package numbers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Numerus {
    private long number;
    final private StringBuilder properties;

    Numerus(long number) {
        this.number = number;
        this.properties = new StringBuilder();
    }

    public boolean isEven() {
        return this.number % 2 == 0;
    }

    public boolean isOdd() {
        return this.number % 2 != 0;
    }

    public boolean isBuzz() {
        return this.number % 7 == 0 || this.number % 10 == 7;
    }

    public boolean isDuck() {
        String stringNumber = Long.toString(this.number);
        String[] parts = stringNumber.split("");

        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals("0")) {
                return true;
            }
        }

        return false;
    }

    public boolean isPalindromic() {
        if (this.number < 10) {
            return true;
        }

        boolean isPalindromic = false;

        String stringNumber = Long.toString(this.number);
        String[] parts = stringNumber.split("");

        for (int head = 0; head < parts.length; head++) {
            int tail = parts.length - 1 - head;
            isPalindromic = parts[head].equals(parts[tail]);

            /* exit early at first non-palindromic number */
            if (!isPalindromic) {
                break;
            }
        }

        return isPalindromic;
    }

    public boolean isGapful() {
        if (this.number < 100) {
            return false;
        }

        long firstDigit = this.number / (long) Math.pow(10, Math.floor(Math.log10(this.number)));
        long lastDigit = this.number % 10;

        int appendedNumber = (int) (firstDigit * 10 + lastDigit);
        return this.number % appendedNumber == 0;
    }

    public boolean isSpy() {
        if (this.number < 10) {
            return true;
        }

        long sumOfDigits = 0;
        long productOfDigits = 1;

        long temp = this.number;
        while (temp > 0) {
            sumOfDigits += temp % 10;
            productOfDigits *= temp % 10;

            temp /= 10;
        }

        return sumOfDigits == productOfDigits;
    }

    public boolean isSquare() {
        long sqrt = (long) Math.sqrt(this.number);
        return sqrt * sqrt == this.number;
    }

    public boolean isSunny() {
        long sqrt = (long) Math.sqrt(this.number + 1);
        return sqrt * sqrt == this.number + 1;
    }

    public boolean isJumping() {
        if (this.number < 10) {
            return true;
        }

        String numberString = Long.toString(this.number);

        for (int i = 0; i < numberString.length() - 1; i++) {
            char digitChar1 = numberString.charAt(i);
            char digitChar2 = numberString.charAt(i+1);
            int digit1 = Character.getNumericValue(digitChar1);
            int digit2 = Character.getNumericValue(digitChar2);
            boolean isJumpingNumber = Math.abs(digit1 - digit2) == 1;

            if (!isJumpingNumber) {
                return false;
            }
        }

        return true;
    }

    public boolean isHappy() {
        Set<Integer> seen = new HashSet<>();

        long temp = this.number;
        while (temp != 1) {
            int sum = 0;
            while (temp > 0) {
                long digit = temp % 10;
                sum += digit * digit;
                temp /= 10;
            }
            if (seen.contains(sum)) {
                return false;
            }
            seen.add(sum);
            temp = sum;
        }
        return true;
    }

    public boolean isSad() {
        return !this.isHappy();
    }

    public void print(long iterator, Set<String> properties) {
        if (iterator < 1) {
            printForOneNumerus();
        } else if (properties.isEmpty()){
            printForRangeOfNumere(iterator);
        } else {
            printForRangeOfNumereAndProperty(iterator, properties);
        }
    }

    private void printForOneNumerus() {
        System.out.println("\nProperties of " + this.number);
        System.out.println("\t even: " + isEven());
        System.out.println("\t odd: " + isOdd());
        System.out.println("\t buzz: " + isBuzz());
        System.out.println("\t duck: " + isDuck());
        System.out.println("\t palindromic: " + isPalindromic());
        System.out.println("\t gapful: " + isGapful());
        System.out.println("\t spy: " + isSpy());
        System.out.println("\t square: " + isSquare());
        System.out.println("\t sunny: " + isSunny());
        System.out.println("\t jumping: " + isJumping());
        System.out.println("\t happy: " + isHappy());
        System.out.println("\t sad: " + !isHappy());
        System.out.println("\n");
    }

    private void printForRangeOfNumere(long iterator) {

        for (int i = 1; i <= iterator; i++) {
            this.properties.append("\t\t ").append(this.number).append(" is");
            this.addProperties();
            this.number++;
        }

        System.out.println(this.properties);
    }

    private void printForRangeOfNumereAndProperty(long iterator, Set<String> propertiesToCheck) {
        int count = 0;
        while (iterator > count) {
            boolean hasNegatedProperty = false;
            boolean hasAllProperties = true;

            for (String property : propertiesToCheck) {
                boolean isNegated = property.startsWith("-");
                if (isNegated) {
                    property = property.substring(1);
                }

                try {
                    char firstLetterUC = Character.toUpperCase(property.charAt(0));

                    String methodBodyLC = property.substring(1).toLowerCase();
                    String dynamicMethodName = "is" + firstLetterUC + methodBodyLC;

                    Method method = getClass().getMethod(dynamicMethodName);

                    boolean hasProperty = (boolean) method.invoke(this);
                    if (isNegated && hasProperty) {
                        hasNegatedProperty = true;
                        break;
                    } else if (!isNegated && !hasProperty) {
                        hasAllProperties = false;
                        break;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    count = (int) iterator;
                }
            }

            if (hasAllProperties && !hasNegatedProperty) {
                this.properties.append("\t\t ")
                        .append(this.number)
                        .append(" is");
                this.addProperties();
                count++;
            }
            this.number++;
        }
        System.out.println(this.properties);
    }

    private void addProperties() {
        if (isEven()) {
            this.properties.append(" even");
        }

        if (isOdd()) {
            this.properties.append(" odd");
        }

        if (isBuzz()) {
            this.properties.append(", buzz");
        }

        if (isDuck()) {
            this.properties.append(", duck");
        }

        if (isPalindromic()) {
            this.properties.append(", palindromic");
        }

        if (isGapful()) {
            this.properties.append(", gapful");
        }

        if (isSpy()) {
            this.properties.append(", spy");
        }

        if (isSquare()) {
            this.properties.append(", square");
        }

        if (isSunny()) {
            this.properties.append(", sunny");
        }

        if (isJumping()) {
            this.properties.append(", jumping");
        }

        if (isHappy()) {
            this.properties.append(", happy");
        }

        if (isSad()) {
            this.properties.append(", sad");
        }

        this.properties.append("\n");
    }
}
