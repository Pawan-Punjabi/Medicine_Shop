package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/epharmacy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "pawan12345";  
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found!");
            System.err.println("Please add the MySQL JDBC driver to your project:");
            System.err.println("1. Right-click on your project in NetBeans");
            System.err.println("2. Select 'Properties'");
            System.err.println("3. Go to 'Libraries'");
            System.err.println("4. Click 'Add Library'");
            System.err.println("5. Select 'MySQL JDBC Driver'");
            throw e;
        } catch (SQLException e) {
            System.err.println("Error connecting to MySQL database!");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("\nPlease check:");
            System.err.println("1. MySQL server is running");
            System.err.println("2. Username and password are correct");
            System.err.println("3. MySQL service is running");
            throw e;
        }
    }
    
    public static void initializeDatabase() {
        try {
            // First connect without database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", USER, PASSWORD);
            java.sql.Statement stmt = conn.createStatement();
            
            // Create database and table if they don't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS epharmacy");
            stmt.executeUpdate("USE epharmacy");
            
            String createTableSQL = 
                "CREATE TABLE IF NOT EXISTS medicines (" +
                "medicine_id VARCHAR(10) PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "price DECIMAL(10,2) NOT NULL," +
                "quantity INT NOT NULL," +
                "expiry_date DATE NOT NULL," +
                "dosage_instructions TEXT" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
            
            stmt.executeUpdate(createTableSQL);
            
            // Check if table is empty and insert sample data if needed
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM medicines");
            rs.next();
            int count = rs.getInt(1);
            
            if (count == 0) {
                String insertSampleData = 
                    "INSERT INTO medicines (medicine_id, name, price, quantity, expiry_date, dosage_instructions) VALUES " +
                    "('M001', 'Aspirin', 127.00, 100, '2024-12-31', 'Take one tablet daily')," +
                    "('M002', 'Paracetamol', 98.00, 150, '2025-01-31', 'Take as needed for pain')," +
                    "('M003', 'Amoxicillin', 230.00, 80, '2024-11-30', 'Take three times daily')," +
                    "('M004', 'Ibuprofen', 47.00, 120, '2025-02-28', 'Take with food')," +
                    "('M005', 'Loratadine', 120.00, 90, '2024-10-31', 'Take once daily')";
                
                stmt.executeUpdate(insertSampleData);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            System.exit(1);
        }
    }
} 