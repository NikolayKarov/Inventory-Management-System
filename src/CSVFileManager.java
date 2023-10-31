import item.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileManager {

    private static final String FILE_PATH = "D:\\Nikolay\\InventoryManagementSystem\\src\\resources\\inventory.csv";

    public static void saveItemToCSV(String fileName, List<InventoryItem> items) {
        try (FileWriter writer = new FileWriter((fileName))) {
            for (InventoryItem item : items) {
                writer.write(item.getItemDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeItemFromCSV(String fileName, int id) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID to remove: ");
        String idToRemove = scanner.nextLine();

        List<String> updatedData = new ArrayList<>();

        boolean found = false;
        for (String line : lines) {
            if (line.startsWith(idToRemove + ",")) {
                found = true;
            } else {
                updatedData.add(line);
            }
        }

        if (!found) {
            System.out.println("item.Item with ID: " + idToRemove + " not found !");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line : updatedData) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("item.Item with ID " + idToRemove + " has been removed successful !");
    }

    public static void readFromCSV(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for (String field : fields) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}