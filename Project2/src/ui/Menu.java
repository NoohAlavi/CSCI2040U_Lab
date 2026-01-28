package ui;

import model.CatalogItem;
import service.CatalogService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final CatalogService service = new CatalogService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Catalog Management System ===");
            System.out.println("1. View Items");
            System.out.println("2. Add Item");
            System.out.println("3. Edit Item");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewItems();
                case 2 -> addItem();
                case 3 -> editItem();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void viewItems() {
        List<CatalogItem> items = service.getAllItems();
        for (CatalogItem item : items) {
            System.out.println(item);
        }
    }

    private void addItem() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();

        if (service.addItem(name, desc)) {
            System.out.println("Item added.");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private void editItem() {
        System.out.print("Enter ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Description: ");
        String desc = scanner.nextLine();

        if (service.editItem(id, name, desc)) {
            System.out.println("Item updated.");
        } else {
            System.out.println("Edit failed.");
        }
    }
}
