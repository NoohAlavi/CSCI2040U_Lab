package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import model.CatalogItem;

public class CatalogService {
    private final List<CatalogItem> items = new ArrayList<>();

    public CatalogService(String csvPath) {
        loadFromCsv(csvPath);
    }

    public List<CatalogItem> getAllItems() {
        return items;
    }

    public CatalogItem getItemById(int id) {
        for (CatalogItem it : items) {
            if (it.getId() == id) return it;
        }
        return null;
    }

    private void loadFromCsv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // header
            if (line == null) return;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;


                String[] parts = line.split(",", 3);
                if (parts.length < 3) continue;

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String desc = parts[2].trim();

                items.add(new CatalogItem(id, name, desc));
            }
        } catch (Exception e) {
            System.out.println("Failed to load CSV: " + e.getMessage());
        }
    }

    private static void writeToCsv(List<CatalogItem> items, String path) {
        try (java.io.FileWriter fw = new java.io.FileWriter(path)) {
            for (CatalogItem item : items) {
                String line = item.getId() + "," + item.getName() + "," + item.getDescription();
                System.out.println(line);
                fw.write(line + "\n");
            }
        } catch (Exception e) {
            System.out.println("Failed to write CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<CatalogItem> items = new ArrayList<>();
        
        items.add(new CatalogItem(0, "test", "desc"));
        items.add(new CatalogItem(1, "test2", "desc2"));
        items.add(new CatalogItem(2, "test3", "desc3"));

        writeToCsv(items, "Lab2/src/data/catalog.csv");
    }
}

