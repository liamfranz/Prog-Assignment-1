import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarManager {
    private List<String> carList;
    private Scanner scanner;

    public CarManager() {
        carList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Car Manager Menu:");
            System.out.println("1. Add a Car");
            System.out.println("2. Remove a Car");
            System.out.println("3. List All Cars");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    listCars();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCar() {
        System.out.print("Enter the name of the car to add: ");
        String carName = scanner.nextLine();
        carList.add(carName);
        System.out.println("Car added successfully.");
    }

    private void removeCar() {
        System.out.print("Enter the name of the car to remove: ");
        String carName = scanner.nextLine();
        if (carList.remove(carName)) {
            System.out.println("Car removed successfully.");
        } else {
            System.out.println("Car not found.");
        }
    }

    private void listCars() {
        if (carList.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            System.out.println("List of cars:");
            for (String car : carList) {
                System.out.println("- " + car);
            }
        }
    }

    public static void main(String[] args) {
        CarManager manager = new CarManager();
        manager.run();
    }
}
