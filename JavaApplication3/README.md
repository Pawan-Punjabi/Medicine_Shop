
# Medicine Shop Application

## 📋 Project Overview
The Medicine Shop application is a Java-based project designed to manage a medicine shop. It allows users to perform the following operations:
- Add new medicines.
- Manage medicine inventory (stock and quantity).
- Handle exceptions for invalid operations.
- Connect to a MySQL database for data storage.

## 🛠️ Technologies Used
- **Language:** Java (JDK 1.8)
- **IDE:** Apache NetBeans
- **Database:** MySQL
- **Build Tool:** Apache Ant

## 🚀 Project Structure
```
Medicine_Shop1
 └── JavaApplication3
      ├── build.xml                    # Build configuration file
      ├── manifest.mf                  # Manifest file
      ├── src                          # Source code
      │     ├── exceptions             # Custom exceptions
      │     ├── interfaces             # Interfaces
      │     ├── main                   # Main execution class
      │     ├── management             # Medicine management logic
      │     ├── models                 # Medicine and Person models
      │     └── storage                # Database and medicine storage
      ├── nbproject                    # NetBeans project files
      └── build                        # Compiled classes
```

## ⚙️ Installation & Setup
1. **Clone the Repository:**
    ```
    git clone https://github.com/Pawan-Punjabi/Medicine_Shop.git
    ```
2. **Open in NetBeans:**
    - Open NetBeans IDE.
    - Select `File > Open Project` and choose the `Medicine_Shop1` folder.

3. **Database Configuration:**
    - Create a MySQL database named `medicine_shop`.
    - Use the following credentials:
      - Username: `root`
      - Password: `root`
    - Ensure you have the MySQL JDBC driver installed.

4. **Run the Application:**
    - Press `F6` to run the project.
    - The terminal will display the available operations.

## 🛑 Exception Handling
The project handles the following exceptions:
- `InvalidMedicineIdException`: Thrown for invalid medicine IDs.
- `InvalidPriceException`: Thrown when entering incorrect medicine prices.
- `InvalidQuantityException`: Thrown for invalid quantities.
- `NoQuantityLeftException`: Thrown when stock is empty.

## 💡 Usage
- Use the console menu to interact with the application.
- Perform operations like adding, updating, and deleting medicines.

## 📚 Authors
- **Group Name:** Refer to `Grou_info.txt`.

## 💻 Guide 
- **For Any Help** Refer to `Fix_Problem.docx`