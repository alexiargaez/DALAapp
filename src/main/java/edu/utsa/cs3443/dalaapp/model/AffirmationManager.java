package edu.utsa.cs3443.dalaapp.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * manages the collection of affirmations for the DALA application
 * handles loading, saving, and retrieving affirmations
 */
public class AffirmationManager {
    private static AffirmationManager instance;

    /**
     * creates and initializes the instance if it doesn't exist.
     * @return the AffirmationManager
     */
    public static AffirmationManager getInstance() {
        if (instance == null) {
            instance = new AffirmationManager();
            instance.loadFromFile();
        }
        return instance;
    }

    private final ArrayList<Affirmation> affirmations = new ArrayList<>();
    private final String dataFilename = "data/affirmations.csv";
    private final Random rand = new Random();

    private AffirmationManager() {}

    /**
     * loads all affirmations from the CSV data file.
     * clears existing affirmations before loading.
     */
    public void loadFromFile() {
        affirmations.clear();
        ensureDataFileExists();

        try (Scanner scanner = new Scanner(new File(dataFilename))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Affirmation a = parseLineToAffirmation(line);
                if (a != null) affirmations.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    /**
     * saves all affirmations to the CSV data file.
     */
    public void saveAllToFile() {
        ensureParentFolder();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilename))) {
            bw.write("Id,Quote,Category,isUserMade");
            bw.newLine();
            for (Affirmation a : affirmations) {
                bw.write(toCsvLine(a));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving affirmations: " + e.getMessage());
        }
    }

    /**
     * retrieves a random affirmation from the specified category.
     *
     * @param category the category to search
     * @return a random Affirmation from the category
     */
    public Affirmation getRandomAffirmationByCategory(String category) {
        ArrayList<Affirmation> tmp = new ArrayList<>();
        for (Affirmation a : affirmations) {
            if (a.getCategory().equalsIgnoreCase(category)) tmp.add(a);
        }
        if (tmp.isEmpty()) return null;
        return tmp.get(rand.nextInt(tmp.size()));
    }

    /**
     * retrieves a random affirmation from all available affirmations.
     *
     * @return a random affirmation, or null if no affirmations exist
     */
    public Affirmation getRandomAffirmation() {
        if (affirmations.isEmpty()) return null;
        return affirmations.get(rand.nextInt(affirmations.size()));
    }


    /**
     * adds a new user-created affirmation to the collection.
     * assigns a unique ID and saves to file.
     * @param text the affirmation quote text
     * @param category the category for this affirmation
     * @return true if successfully added, false otherwise
     */
    public boolean addUserAffirmation(String text, String category) {
        if (text == null || text.isBlank() || category == null || category.isBlank()) return false;
        int id = nextId();
        affirmations.add(new Affirmation(id, text.trim(), category.trim(), true));
        saveAllToFile();
        return true;
    }

    /**
     * ensures the parent directory for the data file exists
     * creates the directory if it doesn't exist
     */
    private void ensureParentFolder() {
        File f = new File(dataFilename);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
    }

    /**
     * ensures the data file exists
     */
    private void ensureDataFileExists() {
        File f = new File(dataFilename);
        if (!f.exists()) {
            ensureParentFolder();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("Id,Quote,Category,isUserMade");
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
            System.out.println("Created " + dataFilename);
        }
    }
    /**
     * Parses a CSV line into an Affirmation object.
     * @param line the CSV line to parse
     * @return the parsed Affirmation
     */
    private Affirmation parseLineToAffirmation(String line) {
        try {
            ArrayList<String> fields = parseCsvLine(line);

            if (fields.size() < 4) return null;

            int id = Integer.parseInt(fields.get(0).trim());
            String quote = fields.get(1).trim();
            String category = fields.get(2).trim();
            boolean user = parseBool(fields.get(3));

            return new Affirmation(id, quote, category, user);
        } catch (Exception e) {
            System.out.println("Bad row: " + line);
            return null;
        }
    }

    /**
     * Parses a CSV line handling quoted fields
     * @param line the CSV line to parse
     * @return a list of field values
     */
    private ArrayList<String> parseCsvLine(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString());

        return fields;
    }

    /**
     * parses a string value as a boolean
     * @param s the string to parse
     * @return true if the string represents true ("true", "1", "yes"), false otherwise
     */
    private boolean parseBool(String s) {
        s = s.trim().toLowerCase();
        return s.equals("true") || s.equals("1") || s.equals("yes");
    }

    /**
     * converts an Affirmation to a CSV line
     * @param a the Affirmation to convert
     * @return a CSV-formatted string
     */
    private String toCsvLine(Affirmation a) {
        String q = a.getQuote();
        if (q.contains(",") || q.contains("\"")) q = "\"" + q.replace("\"","\"\"") + "\"";
        String c = a.getCategory();
        if (c.contains(",") || c.contains("\"")) c = "\"" + c.replace("\"","\"\"") + "\"";
        return a.getId() + "," + q + "," + c + "," + a.isUserMade();
    }

    /**
     * calculates the next available ID for a new affirmation
     * @return the next unique ID
     */
    private int nextId() {
        int max = 0;
        for (Affirmation a : affirmations) if (a.getId() > max) max = a.getId();
        return max + 1;
    }
    public int getAffirmationCount() {
        return affirmations.size();
    }
}


