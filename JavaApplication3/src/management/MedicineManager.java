package management;

import models.Medicine;
import storage.MedicineStorage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import exceptions.NoQuantityLeftException;
import exceptions.InvalidMedicineIdException;
import exceptions.InvalidQuantityException;
import exceptions.InvalidPriceException;

public class MedicineManager {
    private MedicineStorage medicineStorage;

    public MedicineManager(MedicineStorage medicineStorage) {
        this.medicineStorage = medicineStorage;
    }

    public boolean addMedicine(String medicineId, String name, double price, int quantity, String expiryDateString, String dosage) throws InvalidPriceException {
        try {
            if (price < 0) {
                throw new InvalidPriceException("Price cannot be negative.");
            }
            LocalDate expiryDate = LocalDate.parse(expiryDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Medicine newMedicine = new Medicine(medicineId, name, price, quantity, expiryDate);
            newMedicine.setDosageInstructions(dosage);
            medicineStorage.addMedicine(newMedicine);
            System.out.println("✅ New medicine added successfully!");
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Please use yyyy-MM-dd.");
            return false;
        }
    }

    public boolean buyMedicine(String medicineId, int quantityToBuy, Scanner scanner) throws InvalidMedicineIdException, InvalidQuantityException, NoQuantityLeftException {
        if (quantityToBuy <= 0) {
            throw new InvalidQuantityException("Quantity to buy must be positive.");
        }

        Medicine medicineToBuy = medicineStorage.getMedicineById(medicineId);

        if (medicineToBuy == null) {
            throw new InvalidMedicineIdException("❌ Medicine not found with ID: " + medicineId);
        }

        if (medicineToBuy.getQuantity() < quantityToBuy) {
            throw new NoQuantityLeftException("❌ Not enough stock. Available: " + medicineToBuy.getQuantity());
        }

        BigDecimal totalPrice = medicineToBuy.getPrice().multiply(BigDecimal.valueOf(quantityToBuy));
        System.out.println("Total price: Rs" + totalPrice);

        // Ask for confirmation
        System.out.print("Confirm purchase (yes/no)? ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            int newQuantity = medicineToBuy.getQuantity() - quantityToBuy;
            medicineStorage.updateMedicineQuantity(medicineId, newQuantity);
            System.out.println("✅ Purchase successful! Thank you.");
            return true;
        } else {
            System.out.println("❌ Purchase cancelled.");
            return false;
        }
    }
} 