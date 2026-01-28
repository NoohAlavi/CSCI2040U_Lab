package ui;

import java.util.List;
import java.util.Scanner;
import model.CatalogItem;
import service.CatalogService;

public class Menu {
    private final CatalogService service = new CatalogService("Lab2/src/data/catalog.csv");
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Catalog ===");
            System.out.println("1) Display items");
            System.out.println("2) View item details (by ID)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> displayItems();
                case "2" -> viewDetails();
                case "0" -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void displayItems() {
        List<CatalogItem> items = service.getAllItems();
        if (items.isEmpty()) {
            System.out.println("(No items found)");
            return;
        }

        System.out.println("\nID | Name");
        System.out.println("---------");
        for (CatalogItem it : items) {
            System.out.println(it.getId() + " | " + it.getName());
        }
    }

    private void viewDetails() {
        System.out.print("Enter ID: ");
        String input = sc.nextLine().trim();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        CatalogItem it = service.getItemById(id);

        if (it == null) {
            System.out.println("Item not found.");
            return;
        }
    }
}

