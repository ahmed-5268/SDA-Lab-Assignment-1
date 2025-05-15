import java.util.ArrayList;
import java.util.Scanner;

// Represents an invoice
class Invoice {
    private String studentId;
    private String feeType;
    private double amount;

    public Invoice(String studentId, String feeType, double amount) {
        this.studentId = studentId;
        this.feeType = feeType;
        this.amount = amount;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFeeType() {
        return feeType;
    }

    public double getAmount() {
        return amount;
    }

    public void displayInvoice() {
        System.out.println("\n--- Invoice Details ---");
        System.out.println("Student ID: " + studentId);
        System.out.println("Fee Type: " + feeType);
        System.out.println("Amount: PKR " + amount);
    }
}

// Represents a student
class Student {
    private String studentId;

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Invoice createInvoice(String feeType, double amount) {
        return new Invoice(studentId, feeType, amount);
    }
}

// The main Fee System that processes invoices
class FeeSystem {
    private ArrayList<Invoice> invoiceDatabase = new ArrayList<>();

    public boolean submitInvoice(Invoice invoice) {
        if (validateInvoice(invoice)) {
            invoiceDatabase.add(invoice);
            System.out.println("\n✅ Invoice submitted successfully!");
            return true;
        } else {
            System.out.println("\n❌ Invalid invoice. Submission failed.");
            return false;
        }
    }

    private boolean validateInvoice(Invoice invoice) {
        return invoice.getAmount() > 0 && invoice.getFeeType() != null && !invoice.getFeeType().isEmpty();
    }

    public void displayAllInvoices() {
        System.out.println("\n=== All Submitted Invoices ===");
        for (Invoice inv : invoiceDatabase) {
            inv.displayInvoice();
        }
    }
}

// Main class
public class SubmitInvoiceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeeSystem feeSystem = new FeeSystem();

        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();

        Student student = new Student(studentId);

        System.out.print("Enter fee type (e.g. Tuition, Hostel): ");
        String feeType = scanner.nextLine();

        System.out.print("Enter fee amount: ");
        double amount = scanner.nextDouble();

        Invoice invoice = student.createInvoice(feeType, amount);

        if (feeSystem.submitInvoice(invoice)) {
            invoice.displayInvoice();
        }

        System.out.println("\nDo you want to view all submitted invoices? (yes/no): ");
        scanner.nextLine(); // consume newline
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            feeSystem.displayAllInvoices();
        }

        scanner.close();
    }
}
