/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudaissdalabassignment;

/**
 *
 * @author lenovo
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

class FeeCalculator {
    private double totalFee = 60000;

    public double getTotalFee() {
        return totalFee;
    }
}

class Payment {
    private String studentId;
    private double amountPaid;
    private double totalFee;
    private String status;

    public Payment(String studentId, double amountPaid, double totalFee) {
        this.studentId = studentId;
        this.amountPaid = amountPaid;
        this.totalFee = totalFee;
        this.status = calculateStatus();
    }

    private String calculateStatus() {
        if (amountPaid >= totalFee) return "Paid";
        else return "Partially Paid";
    }

    public double getRemainingAmount() {
        return totalFee - amountPaid;
    }

    public String getStatusWithDueDate() {
        if (status.equals("Paid")) {
            return "Paid";
        } else {
            return "Partially Paid (Due Date: " + LocalDate.now().plusDays(7) + ")";
        }
    }

    public String getStudentId() { return studentId; }
    public double getAmountPaid() { return amountPaid; }
    public double getTotalFee() { return totalFee; }
    public String getStatus() { return status; }
}

class PaymentProcessor {
    private NotificationService notifier = new NotificationService();

    public boolean receivePayment(Payment payment) {
        System.out.println("Payment received from " + payment.getStudentId());
        return true;
    }
}

class NotificationService {}

class Student {
    private String studentId;
    private String name;
    private String email;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class VoucherGenerator {
    public String generateVoucher(String studentId) {
        return "VCHR-" + studentId + "-" + System.currentTimeMillis();
    }
}


class StudentFeeController {
    public String processStudentFee(String studentId, String name, String email, double amountPaid) {
        Student student = new Student(studentId, name, email);
        FeeCalculator calculator = new FeeCalculator();
        double totalFee = calculator.getTotalFee();

        Payment payment = new Payment(studentId, amountPaid, totalFee);
        PaymentProcessor processor = new PaymentProcessor();
        processor.receivePayment(payment);

        VoucherGenerator voucherGen = new VoucherGenerator();
        String voucher = voucherGen.generateVoucher(studentId);

        return "Student: " + name +
                "\nEmail: " + email +
                "\nTotal Fee: " + totalFee +
                "\nAmount Paid: " + payment.getAmountPaid() +
                "\nRemaining: " + payment.getRemainingAmount() +
                "\nPayment Status: " + payment.getStatusWithDueDate() +
                "\nVoucher: " + voucher;
    }
}

public class Main {
    public static void main(String[] args) {
       
        JFrame frame = new JFrame("Student Fee Payment");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(10, 20, 100, 25);
        panel.add(studentIdLabel);

        JTextField studentIdText = new JTextField(20);
        studentIdText.setBounds(150, 20, 250, 25);
        panel.add(studentIdText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 60, 100, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 60, 250, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 100, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(150, 100, 250, 25);
        panel.add(emailText);

        JLabel paidLabel = new JLabel("Amount Paid:");
        paidLabel.setBounds(10, 140, 100, 25);
        panel.add(paidLabel);

        JTextField paidText = new JTextField(20);
        paidText.setBounds(150, 140, 250, 25);
        panel.add(paidText);

        JButton payButton = new JButton("Submit Payment");
        payButton.setBounds(150, 180, 150, 30);
        panel.add(payButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(10, 230, 410, 200);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        panel.add(outputArea);

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdText.getText();
                String name = nameText.getText();
                String email = emailText.getText();
                double amountPaid = 0;

                try {
                    amountPaid = Double.parseDouble(paidText.getText());
                } catch (NumberFormatException ex) {
                    outputArea.setText("Please enter a valid number for amount paid.");
                    return;
                }

                StudentFeeController controller = new StudentFeeController();
                String result = controller.processStudentFee(studentId, name, email, amountPaid);
                outputArea.setText(result);
            }
        });
    }
}
