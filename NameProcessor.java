import java.io.*;
import java.util.ArrayList;

public class NameProcessor {
    private String filePath;

    public NameProcessor(String filePath) {
        this.filePath = filePath;
    }

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