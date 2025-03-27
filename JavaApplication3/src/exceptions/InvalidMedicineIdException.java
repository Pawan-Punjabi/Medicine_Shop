package exceptions;

public class InvalidMedicineIdException extends Exception {
    public InvalidMedicineIdException(String message) {
        super(message);
    }
}