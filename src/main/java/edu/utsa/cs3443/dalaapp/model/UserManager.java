package edu.utsa.cs3443.dalaapp.model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * manages user accounts for the DALA application
 * handles user registration and authentication
 */
public class UserManager {
    private static UserManager instance;
    private final ArrayList<User> users = new ArrayList<>();
    private final String dataFilename = "data/users.csv";
    private User currentUser;
    private boolean rememberMe = false;

    /**
     * automatically loads users from file upon creation
     */
    private UserManager() {
        loadFromFile();
    }

    /**
     * Creates the instance if it doesn't exist
     * @return UserManager
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * loads all user accounts from the CSV data file
     * clears existing users before loading
     * creates the data file if it doesn't exist
     */
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

    /**
     * saves all user accounts to the CSV data file
     * rewrites the file with current user data
     */
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

    /**
     * authenticates a user with the provided credentials
     * sets the current user if authentication is successful.
     * @param username the username to authenticate
     * @param password the password to verify
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    /**
     * registers a new user account with the provided information
     * validates that all fields are non-blank and username is unique
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     * @param username the username (must be unique)
     * @param password the password for the account
     * @return true if signup successful, false if validation fails or username exists
     */

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

    /**
     * checks if a username already exists in the system.
     * @param username the username to check
     * @return true if username exists, false otherwise
     */
    public boolean userExists(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * logs out the current user
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * gets the currently logged-in user.
     * @return the current User
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * sets the "Remember Me" preference for login
     * @param remember true to remember the user, false otherwise
     */
    public void setRememberMe(boolean remember) {
        this.rememberMe = remember;
    }

    /**
     * gets the current "Remember Me" preference
     * @return true if remember me is enabled, false otherwise
     */
    public boolean isRememberMe() {
        return rememberMe;
    }


    /**
     * ensures the parent directory for the data file exists.
     */
    private void ensureParentFolder() {
        File f = new File(dataFilename);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
    }

    /**
     * ensures the user data file exists
     */
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

    /**
     * parses a CSV line into a User object
     * @param line the CSV line to parse
     * @return the parsed User
     */
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

    /**
     * parses a CSV line handling quoted fields
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
     * converts a User to a CSV line
     * @param u the User to convert
     * @return a CSV-formatted string
     */
    private String toCsvLine(User u) {
        String firstName = escapeCsv(u.getFirstName());
        String lastName = escapeCsv(u.getLastName());
        String email = escapeCsv(u.getEmail());
        String username = escapeCsv(u.getUsername());
        String password = escapeCsv(u.getPassword());
        return firstName + "," + lastName + "," + email + "," + username + "," + password;
    }

    /**
     * escapes special CSV characters in a field value
     * wraps fields containing commas or quotes in double quotes
     * @param field the field value to escape
     * @return the escaped field value
     */
    private String escapeCsv(String field) {
        if (field.contains(",") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
}