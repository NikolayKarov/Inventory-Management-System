import item.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileManager {

    private static final String FILE_PATH = "D:\\Nikolay\\InventoryManagementSystem\\src\\resources\\inventory.csv";

    /* This method takes a filename and a list of InventoryItem objects as input and saves the item data to a CSV file.
    It uses a FileWriter to write the item descriptions to the file.
    If an error occurs during file writing, it will print an error message. */
    public static void saveItemToCSV(String fileName, List<InventoryItem> items) {
        try (FileWriter writer = new FileWriter((fileName))) {
            for (InventoryItem item : items) {
                writer.write(item.getItemDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* This method allows removing an item from the CSV file based on its ID.
    It reads the CSV file, prompts the user for the ID to remove, and then updates the file by excluding
    the item with the specified ID. It also provides error handling for file I/O issues. */
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
            System.out.println("Item with ID: " + idToRemove + " not found !");
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

        System.out.println("Item with ID " + idToRemove + " has been removed successful !");
    }

    /* his method reads and prints the content of the CSV file to the console.
    It splits each line into fields using a comma as the delimiter and prints each field.
    It handles file I/O exceptions and prints error messages if necessary. */
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

    /* This method reads the CSV file and returns a list of InventoryItem objects containing the inventory data.
     It parses each line from the CSV file into item attributes and creates InventoryItem instances,
     adding them to a list. Any file I/O errors are handled with error messages. */
    public static List<InventoryItem> getInventoryFromFile() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(", ");
                InventoryItem inventoryItem = new InventoryItem();
                inventoryItem.setItemID(Integer.parseInt(item[0]));
                inventoryItem.setName(item[1]);
                inventoryItem.setCategory(item[2]);
                inventoryItem.setPrice(Double.parseDouble(item[3]));
                inventoryItem.setQuantity(Integer.parseInt(item[4]));
                inventoryItems.add(inventoryItem);
            }
            reader.close();
            return inventoryItems;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventoryItems;
    }

    /* This method takes a list of InventoryItem objects and prints their descriptions to the console.
    It first retrieves the inventory data from the CSV file using getInventoryFromFile() and then prints each item's description.
    This method provides a convenient way to display the current inventory */
    public static void printInventory(List<InventoryItem> inventory) {
        inventory = getInventoryFromFile();
        for (InventoryItem inventoryItem : inventory) {
            System.out.println(inventoryItem.getItemDescription());
        }
    }
}