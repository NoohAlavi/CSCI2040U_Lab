import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DatabaseInteraction {

    public static final String DATA_DELIMITER = ",";
    public static final String DATA_FILEPATH = "Lab2/src/data/catalog.csv";

    public static List<List<String>> getData() {
        List<List<String>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(DATA_FILEPATH))) {
            int i = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                i++;

                if (i <= 1) continue; // skip the first iteration so headers are not included in the data

                String[] values = line.split(DATA_DELIMITER);
                List<String> lineData = Arrays.asList(values);

                data.add(lineData);
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }
        
        return data;
    }

    public boolean saveData(String[] data) {

        return true;
    }

    public static void main(String[] args) {
        List<List<String>> data = getData();
        System.out.println(data);
    }
}