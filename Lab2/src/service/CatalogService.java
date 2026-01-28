package service;

import model.CatalogItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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
    
    public void saveToCsv(String csvPath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvPath))) {
            pw.println("id,name,description");

            
            for (CatalogItem item : items) {
                pw.println(
                        item.getId() + "," +
                        item.getName() + "," +
                        item.getDescription()
                );
            }

        } catch (IOException e) {
            System.out.println("Failed to save catalog to CSV: " + e.getMessage());
        }
    }

}


