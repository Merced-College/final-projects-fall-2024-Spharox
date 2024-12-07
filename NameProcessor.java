//Pablo Mendoza 
//CPSC-39
//12/6/2024

import java.io.*;
import java.util.ArrayList;

public class NameProcessor {
    private String filePath;

    public NameProcessor(String filePath) {
        this.filePath = filePath;
    }

    //returns arraylist of first names from the Names.txt file
    public ArrayList<String> getFirstNames() {
        ArrayList<String> firstNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                firstNames.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNames;
    }

    //returns arraylist of last names from the Names.txt file
    public ArrayList<String> getLastNames() {
        ArrayList<String> lastNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                lastNames.add(parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastNames;
    }
}