import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String format(LocalDate date) {
        if (date == null) return "N/A";
        return date.format(FORMATTER);
    }

    public static LocalDate calculateDueDate(LocalDate issueDate, int borrowDays) {
        return issueDate.plusDays(borrowDays);
    }
}