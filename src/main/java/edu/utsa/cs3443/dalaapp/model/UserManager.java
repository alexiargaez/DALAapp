package edu.utsa.cs3443.dalaapp.model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    private static UserManager instance;
    private final ArrayList<User> users = new ArrayList<>();
    private final String dataFilename = "data/users.csv";
    private User currentUser;
    private boolean rememberMe = false;

    private UserManager() {
        loadFromFile();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void loadFromFile() {
        users.clear();
        ensureDataFileExists();

        try (Scanner scanner = new Scanner(new File(dataFilename))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                User u = parseLineToUser(line);
                if (u != null) users.add(u);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    public void saveAllToFile() {
        ensureParentFolder();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilename))) {
            bw.write("FirstName,LastName,Email,Username,Password");
            bw.newLine();
            for (User u : users) {
                bw.write(toCsvLine(u));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }


    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public boolean signup(String firstName, String lastName, String email, String username, String password) {
        if (firstName == null || firstName.isBlank() ||
                lastName == null || lastName.isBlank() ||
                email == null || email.isBlank() ||
                username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            return false;
        }

        // Check if username already exists
        if (userExists(username)) {
            return false;
        }

        User newUser = new User(firstName.trim(), lastName.trim(), email.trim(), username.trim(), password.trim());
        users.add(newUser);
        saveAllToFile();
        return true;
    }

    public boolean userExists(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setRememberMe(boolean remember) {
        this.rememberMe = remember;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }


    private void ensureParentFolder() {
        File f = new File(dataFilename);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
    }

    private void ensureDataFileExists() {
        File f = new File(dataFilename);
        if (!f.exists()) {
            ensureParentFolder();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("FirstName,LastName,Email,Username,Password");
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Error creating user data file: " + e.getMessage());
            }
            System.out.println("Created " + dataFilename);
        }
    }

    private User parseLineToUser(String line) {
        try {
            ArrayList<String> fields = parseCsvLine(line);
            if (fields.size() < 5) return null;

            String firstName = fields.get(0).trim();
            String lastName = fields.get(1).trim();
            String email = fields.get(2).trim();
            String username = fields.get(3).trim();
            String password = fields.get(4).trim();

            return new User(firstName, lastName, email, username, password);
        } catch (Exception e) {
            System.out.println("Bad user row: " + line);
            return null;
        }
    }

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

    private String toCsvLine(User u) {
        String firstName = escapeCsv(u.getFirstName());
        String lastName = escapeCsv(u.getLastName());
        String email = escapeCsv(u.getEmail());
        String username = escapeCsv(u.getUsername());
        String password = escapeCsv(u.getPassword());
        return firstName + "," + lastName + "," + email + "," + username + "," + password;
    }

    private String escapeCsv(String field) {
        if (field.contains(",") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
}