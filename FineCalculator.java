import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    private static final double FINE_PER_DAY = 5.0; // Rs. 5 per overdue day

    public static double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
            return overdueDays * FINE_PER_DAY;
        }
        return 0.0;
    }
}