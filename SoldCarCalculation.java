
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SoldCarCalculation {
    private List<Car> carDatabase;
    private List<SalesTransaction> salesTransactions;

    public SoldCarCalculation(List<Car> carDatabase, List<SalesTransaction> salesTransactions) {
        this.carDatabase = carDatabase;
        this.salesTransactions = salesTransactions;
    }

    // Method to get sold cars in a month
    public List<Car> getSoldCarsInMonth(int month, int year) {
        List<Car> soldCars = new ArrayList<>();
        for (SalesTransaction transaction : salesTransactions) {
            // Extract month and year from transaction date (assuming transactionDate is in "yyyy-MM-dd" format)
            String[] parts = transaction.getTransactionDate().split("-");
            int transactionYear = Integer.parseInt(parts[0]);
            int transactionMonth = Integer.parseInt(parts[1]);

            if (transactionYear == year && transactionMonth == month) {
                for (Object item : transaction.getPurchasedItems()) {
                    if (item instanceof Car) {
                        soldCars.add((Car) item);
                    }
                }
            }
        }
        return soldCars;
    }

    public static void main(String[] args) {
        // Create mock car data
        Car car1 = new Car("c-001", "Toyota", "Corolla", 2020, 15000, "White", true, 20000.0, "No issues");
        Car car2 = new Car("c-002", "Honda", "Civic", 2019, 30000, "Black", false, 18000.0, "Minor scratches");
        Car car3 = new Car("c-003", "Ford", "Focus", 2018, 45000, "Blue", true, 15000.0, "Serviced regularly");

        // Create mock sales transactions
        SalesTransaction transaction1 = new SalesTransaction("T1", "2024-08-01", List.of(car1));
        SalesTransaction transaction2 = new SalesTransaction("T2", "2024-08-15", List.of(car3));
        SalesTransaction transaction3 = new SalesTransaction("T3", "2024-07-10", List.of(car2));

        List<Car> carDatabase = new ArrayList<>(Arrays.asList(car1, car2, car3));
        List<SalesTransaction> salesTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        // Create SoldCarCalculation instance
        SoldCarCalculation calculation = new SoldCarCalculation(carDatabase, salesTransactions);

        // Ask the user to input the month and year
        System.out.println("-------------------------");
        System.out.println("MONTHLY SOLD CAR");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        // Get the list of cars sold for the input month and year
        List<Car> soldCars = calculation.getSoldCarsInMonth(month, year);

        // Display the result
        if (soldCars.isEmpty()) {
            System.out.println("No cars were sold in " + month + "/" + year);
        } else {
            System.out.println("Cars sold in " + month + "/" + year + ":");
            for (Car car : soldCars) {
                System.out.println("ID: " + car.getCarID() + ", Make: " + car.getMake() + ", Model: " + car.getModel() +
                        ", Year: " + car.getYear() + ", Mileage: " + car.getMileage() + ", Color: " + car.getColor() +
                        ", Price: " + car.getPrice() + ", Notes: " + car.getNotes());
            }
        }

        scanner.close();
    }
}
