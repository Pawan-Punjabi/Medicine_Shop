package storage;

import models.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineStorage {
    private static final String DB_NAME = "epharmacy_APKS";

    public MedicineStorage() {
        DatabaseConnection.initializeDatabase();
    }

    public Medicine getMedicineById(String medicineId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setCatalog(DB_NAME);
            String sql = "SELECT * FROM medicines WHERE medicine_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, medicineId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Medicine medicine = new Medicine(
                    rs.getString("medicine_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getDate("expiry_date").toLocalDate()
                );
                medicine.setDosageInstructions(rs.getString("dosage_instructions"));
                return medicine;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error getting medicine: " + e.getMessage());
        }
        return null;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setCatalog(DB_NAME);
            String sql = "SELECT * FROM medicines";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Medicine medicine = new Medicine(
                    rs.getString("medicine_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getDate("expiry_date").toLocalDate()
                );
                medicine.setDosageInstructions(rs.getString("dosage_instructions"));
                medicines.add(medicine);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error getting all medicines: " + e.getMessage());
            e.printStackTrace();
        }
        return medicines;
    }

    public void addMedicine(Medicine medicine) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setCatalog(DB_NAME);
            String sql = "INSERT INTO medicines (medicine_id, name, price, quantity, expiry_date, dosage_instructions) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, medicine.getMedicineId());
            pstmt.setString(2, medicine.getName());
            pstmt.setDouble(3, medicine.getPrice().doubleValue());
            pstmt.setInt(4, medicine.getQuantity());
            pstmt.setDate(5, java.sql.Date.valueOf(medicine.getExpiryDate()));
            pstmt.setString(6, medicine.getDosageInstructions());
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error adding medicine: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateMedicineQuantity(String medicineId, int newQuantity) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setCatalog(DB_NAME);
            String sql = "UPDATE medicines SET quantity = ? WHERE medicine_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, medicineId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error updating medicine quantity: " + e.getMessage());
            e.printStackTrace();
        }
    }
}