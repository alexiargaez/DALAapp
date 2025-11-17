package edu.utsa.cs3443.dalaapp.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AffirmationManager {
    private static AffirmationManager instance;

    public static AffirmationManager getInstance() {
        if (instance == null) {
            instance = new AffirmationManager();
            instance.loadFromFile();
        }
        return instance;
    }

    private final ArrayList<Affirmation> affirmations;
    private final String dataFilename;

    private final Random rand = new Random();

    private AffirmationManager() {
        this.affirmations = new ArrayList<>();
        this.dataFilename = "data/affirmations.csv";
    }

    public void loadFromFile() {
        affirmations.clear();
        ensureDataFileExists();

        try (Scanner scanner = new Scanner(new File(dataFilename))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Affirmation a = parseLineToAffirmation(line);
                if (a != null) {
                    affirmations.add(a);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

        private void ensureParentFolder() {
            File f = new File(dataFilename);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
        }
        public void saveAllToFile () {
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

    private Affirmation parseLineToAffirmation(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 4) return null;

        try {
            int id = Integer.parseInt(p[0].trim());
            String quote = p[1].trim();
            String category = p[2].trim();
            boolean user = parseBool(p[3]);
            return new Affirmation(id, quote, category, user);
        } catch (Exception e) {
            System.out.println("Bad row: " + line);
            return null;
        }
    }

    private boolean parseBool(String s) {
        s = s.trim().toLowerCase();
        return s.equals("true") || s.equals("1") || s.equals("yes");
    }

    private String toCsvLine(Affirmation a) {
        String q = a.getQuote();
        if (q.contains(",") || q.contains("\"")) {
            q = "\"" + q.replace("\"", "\"\"") + "\"";
        }
        String c = a.getCategory();
        if (c.contains(",") || c.contains("\"")) {
            c = "\"" + c.replace("\"", "\"\"") + "\"";
        }
        return a.getId() + "," + q + "," + c + "," + a.isUserMade();
    }

    private int nextId() {
        int max = 0;
        for (Affirmation a : affirmations) {
            if (a.getId() > max) max = a.getId();
        }
        return max + 1;


    }
}

