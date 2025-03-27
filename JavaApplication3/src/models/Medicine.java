package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import interfaces.Dosageable;

public class Medicine implements Dosageable {
    private final String medicineId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private LocalDate expiryDate;
    private String dosageInstructions; // Added field for dosage instructions

    public Medicine(String medicineId, String name, double price, int quantity, LocalDate expiryDate) {
        this.medicineId = medicineId;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.dosageInstructions = null; // Initially, no dosage instructions
    }

    // Getters and setters for existing fields (medicineId, name, price, quantity, expiryDate)

    @Override
    public String getDosageInstructions() {
        return dosageInstructions;
    }

    @Override
    public void setDosageInstructions(String instructions) {
        this.dosageInstructions = instructions;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void displayDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Medicine ID: " + medicineId);
        System.out.println("Name: " + name);
        System.out.println("Price: Rs" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Expiry Date: " + expiryDate.format(formatter));
        if (dosageInstructions != null) {
            System.out.println("Dosage Instructions: " + dosageInstructions);
        }
    }
    // Other methods (getMedicineId, getName, getPrice, getQuantity, getExpiryDate, setQuantity, isExpired)
}