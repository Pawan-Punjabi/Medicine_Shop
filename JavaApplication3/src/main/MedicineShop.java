package main;

import management.MedicineManager;
import storage.MedicineStorage;
import storage.DatabaseConnection;
import models.Medicine;

import java.util.Scanner;
import exceptions.NoQuantityLeftException;
import exceptions.InvalidMedicineIdException;
import exceptions.InvalidQuantityException;
import exceptions.InvalidPriceException;

public class MedicineShop {
    public static void main(String[] args) {
        try {
            // Initialize database connection
            DatabaseConnection.initializeDatabase();
            
            MedicineStorage medicineStorage = new MedicineStorage();
            MedicineManager medicineManager = new MedicineManager(medicineStorage);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n==== WELCOME TO MEDICINE SHOP ====");
                System.out.println("1. Show List of Medicines");
                System.out.println("2. Buy Medicine");
                System.out.println("3. Get Details of a Medicine");
                System.out.println("4. Add New Medicine (Admin)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n--- List of Medicines ---");
                        for (Medicine medicine : medicineStorage.getAllMedicines()) {
                            System.out.println(medicine.getMedicineId() + ": " + medicine.getName() + " - Rs" + medicine.getPrice());
                        }
                        break;

                    case 2:
                        System.out.print("Enter Medicine ID to buy: ");
                        String medicineId = scanner.nextLine();

                        System.out.print("Enter quantity to buy: ");
                        int quantityToBuy = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        try {
                            medicineManager.buyMedicine(medicineId, quantityToBuy, scanner);
                        } catch (InvalidMedicineIdException | InvalidQuantityException | NoQuantityLeftException e) {
                            System.out.println(e.getMessage()); // Print the exception message
                        }

                        break;

                    case 3:
                        System.out.print("Enter Medicine ID to get details: ");
                        String medicineIdDetails = scanner.nextLine();
                        Medicine medicineDetails = medicineStorage.getMedicineById(medicineIdDetails);

                        if (medicineDetails == null) {
                            System.out.println("❌ Medicine not found with ID: " + medicineIdDetails);
                        } else {
                            System.out.println("\n--- Medicine Details ---");
                            medicineDetails.displayDetails();
                        }
                        break;
                    case 4:
                        System.out.println("\n--- Add New Medicine (Admin) ---");

                        System.out.print("Enter Medicine ID: ");
                        String medicineIdNew = scanner.nextLine();

                        System.out.print("Enter Medicine Name: ");
                        String medicineNameNew = scanner.nextLine();

                        System.out.print("Enter Medicine Price: ");
                        double medicinePriceNew = scanner.nextDouble();

                        System.out.print("Enter Medicine Quantity: ");
                        int medicineQuantityNew = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter Medicine Expiry Date (yyyy-MM-dd): ");
                        String expiryDateStringNew = scanner.nextLine();

                        System.out.print("Enter Dosage Instruction: ");
                        String dosage = scanner.nextLine();
                        try{
                            medicineManager.addMedicine(medicineIdNew, medicineNameNew, medicinePriceNew, medicineQuantityNew, expiryDateStringNew, dosage);
                        } catch (InvalidPriceException e) {
                             System.out.println(e.getMessage()); // Print the exception message
                        }
                        break;

                    case 5:
                        System.out.println("Exiting... Thank you!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("❌ Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error initializing the application: " + e.getMessage());
            System.exit(1);
        }
    }
}