package numbers;

import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private Set<String> properties;

    UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        this.displayInstructions();

        while (true) {
            this.properties = new HashSet<>();
            System.out.print("Enter a request: ");
            String input = scanner.nextLine().trim();
            if (this.isInputNullOrEmpty(input)) {
                continue;
            }

            String[] parts = input.split(" ");

            long startingNumber = this.validateInput(parts, 0);
            if (startingNumber < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                continue;
            }

            if (startingNumber == 0) {
                System.out.println("\nGoodbye!");
                break;
            }

            long iterator = parts.length < 2 ? 0 : this.validateInput(parts, 1);
            if (iterator < 0) {
                System.out.println("\nThe second parameter should be a natural number or zero.");
                continue;
            }

            if (parts.length > 2) {
                for (int i = 2; i < parts.length; i++) {
                    this.properties.add(parts[i].toUpperCase());
                }
            }

            if (!Properties.getInvalidProperties(this.properties).isEmpty()) {
                printError(Properties.getInvalidProperties(this.properties));
                continue;
            }

            if (Properties.hasConflicts(this.properties)) {
                continue;
            }

            Numerus numerus = new Numerus(startingNumber);
            numerus.print(iterator, this.properties);
        }
    }

    private void displayInstructions() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list");
        System.out.println("\t the first parameter represents a starting number");
        System.out.println("\t the second parameter shows how many consecutive numbers are to be printed");
        System.out.println("- two natural numbers and properties to search for");
        System.out.println("- a property preceded by minus must not be present in numbers");
        System.out.println("- separate the parameters with one space");
        System.out.println("- enter 0 to exit\n");
    }

    private boolean isInputNullOrEmpty(String input) {
        return input == null || input.equals("");
    }

    private long validateInput(String[] parts, int index) {
        if (index < 0 || index > 1) {
            return -1;
        }

        try {
            return Long.parseLong(parts[index]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return -1;
        }
    }

    private void printError(List<String> invalidProperties) {
        if (invalidProperties.size() > 1) {
            System.out.println("The properties " + invalidProperties + " are wrong.");
        } else {
            System.out.println("The property [" + invalidProperties.get(0) + "] is wrong.");
        }

        System.out.println("Available properties: " + Arrays.toString(Properties.values()));
    }
}
