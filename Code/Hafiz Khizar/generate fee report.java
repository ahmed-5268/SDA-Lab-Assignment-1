public class FeeReportUI {
    public static void main(String[] args) {
        FeeReportController controller = new FeeReportController();
        String filters = "2024"; // Example filter
        controller.generateReport(filters);
    }
}
public class FeeReportController {
    private FeeReportService service = new FeeReportService();

    public void generateReport(String filters) {
        String report = service.getReport(filters);
        System.out.println("Generated Report:\n" + report);
    }
}
public class FeeReportService {
    private FeeReportRepository repository = new FeeReportRepository();

    public String getReport(String filters) {
        return repository.fetchReport(filters);
    }
}
public class FeeReportRepository {
    public String fetchReport(String filters) {
        // Simulated database report
        return "Fee Report for " + filters + ":\n" +
               "Student: Khizar Ali\n" +
               "Program: Software Engineering\n" +
               "Total Fee: Rs. 50,000\n" +
               "Paid: Rs. 30,000\n" +
               "Remaining: Rs. 20,000";
    }
}
