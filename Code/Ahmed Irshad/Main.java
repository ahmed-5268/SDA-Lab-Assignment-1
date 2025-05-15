import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Client {
    private String clientId;
    private String name;
    private String contact;
    private String course;

    public Client(String name, String contact, String course) {
        this.name = name;
        this.contact = contact;
        this.course = course;
        this.clientId = generateClientId();
    }

    private String generateClientId() {
        return "CLT" + System.currentTimeMillis();  // Simple unique ID
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getCourse() {
        return course;
    }
}

public class ClientRepository {
    private List<Client> clientList;

    public ClientRepository() {
        clientList = new ArrayList<>();
    }

    public void saveClient(Client client) {
        clientList.add(client);
        System.out.println("Client saved successfully.");
    }

    public List<Client> getAllClients() {
        return clientList;
    }
}

public class RegistrationService {
    private ClientRepository clientRepository;

    public RegistrationService() {
        clientRepository = new ClientRepository();
    }

    public boolean registerClient(String name, String contact, String course) {
        if (validateClient(name, contact, course)) {
            Client newClient = new Client(name, contact, course);
            clientRepository.saveClient(newClient);
            System.out.println("Client registered successfully. Client ID: " + newClient.getClientId());
            return true;
        } else {
            System.out.println("Invalid data. Registration failed.");
            return false;
        }
    }

    private boolean validateClient(String name, String contact, String course) {
        return name != null && !name.trim().isEmpty()
            && contact != null && !contact.trim().isEmpty()
            && course != null && !course.trim().isEmpty();
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationService registrationService = new RegistrationService();

        System.out.println("=== Fee System: Register New Client ===");

        System.out.print("Enter Client Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Course Name: ");
        String course = scanner.nextLine();

        registrationService.registerClient(name, contact, course);

        scanner.close();
    }
}
