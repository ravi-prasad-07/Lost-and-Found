import java.util.Scanner;

/**
 * Lost & Found Portal - Main Console App
 * Entry Point
 * Ravi : Main + Console UI
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DBConnection.initDB();

        System.out.println("=============================");
        System.out.println("  CAMPUS LOST & FOUND PORTAL ");
        System.out.println("=============================");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": handleLogin(); break;
                case "2": handleRegister(); break;
                case "3":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void handleLogin() {
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(email, password);

        if (user == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("\nWelcome, " + user.getName() + "! [" + user.getRole() + "]");

        if (user.getRole().equals("admin")) {
            adminMenu(user);
        } else {
            studentMenu(user);
        }
    }

    static void handleRegister() {
        System.out.print("Full Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        User user = new User(0, name, email, password, "student");
        UserDAO userDAO = new UserDAO();

        if (userDAO.register(user)) {
            System.out.println("Registered successfully! You can now login.");
        } else {
            System.out.println("Registration failed. Email may already exist.");
        }
    }

    static void studentMenu(User user) {
        ItemDAO itemDAO = new ItemDAO();
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. View All Reports");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": reportItem(user, "lost", itemDAO); break;
                case "2": reportItem(user, "found", itemDAO); break;
                case "3": viewItems(itemDAO); break;
                case "4": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void adminMenu(User user) {
        ItemDAO itemDAO = new ItemDAO();
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Reports");
            System.out.println("2. Mark Item Resolved");
            System.out.println("3. Delete Item");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": viewItems(itemDAO); break;
                case "2":
                    System.out.print("Enter Item ID to resolve: ");
                    int rid = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(itemDAO.resolveItem(rid) ? "Marked resolved." : "Failed.");
                    JsonExporter.export();
                    break;
                case "3":
                    System.out.print("Enter Item ID to delete: ");
                    int did = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(itemDAO.deleteItem(did) ? "Deleted." : "Failed.");
                    JsonExporter.export();
                    break;
                case "4": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void reportItem(User user, String status, ItemDAO itemDAO) {
        System.out.print("Item Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Description: ");
        String desc = sc.nextLine().trim();
        System.out.print("Category (Electronics/Accessories/Stationery/Clothing/Other): ");
        String cat = sc.nextLine().trim();
        System.out.print("Location on campus: ");
        String loc = sc.nextLine().trim();

        Item item = new Item(0, title, desc, cat, status, loc, user.getId(),
                             java.time.LocalDate.now().toString(), false);

        if (itemDAO.addItem(item)) {
            System.out.println("Report submitted successfully!");
            JsonExporter.export();
        } else {
            System.out.println("Failed to submit report.");
        }
    }

    static void viewItems(ItemDAO itemDAO) {
        var items = itemDAO.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No reports found.");
            return;
        }
        System.out.println("\n--- All Reports ---");
        for (Item it : items) {
            System.out.printf("[%d] %s | %s | %s | %s | %s%n",
                it.getId(), it.getStatus().toUpperCase(),
                it.getTitle(), it.getCategory(),
                it.getLocation(), it.getReportDate());
        }
    }
}
