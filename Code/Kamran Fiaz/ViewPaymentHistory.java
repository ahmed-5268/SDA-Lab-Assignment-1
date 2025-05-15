import java.sql.*;
import java.util.Scanner;

public class ViewPaymentHistory {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fee_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== View Payment History ===");
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();

        viewPaymentHistory(studentId);
    }

    public static void viewPaymentHistory(int studentId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC driver (optional in modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to fetch payments for the given student ID
            String sql = "SELECT payment_id, payment_date, amount_paid FROM payments WHERE student_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);

            resultSet = statement.executeQuery();

            System.out.println("\n--- Payment History ---");
            boolean hasRecords = false;
            while (resultSet.next()) {
                int paymentId = resultSet.getInt("payment_id");
                Date paymentDate = resultSet.getDate("payment_date");
                double amountPaid = resultSet.getDouble("amount_paid");

                System.out.println("Payment ID: " + paymentId +
                                   " | Date: " + paymentDate +
                                   " | Amount: Rs. " + amountPaid);
                hasRecords = true;
            }

            if (!hasRecords) {
                System.out.println("No payment history found for Student ID: " + studentId);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources.");
            }
        }
    }
}
