package edu.utsa.cs3443.dalaapp.model;

/**
 * represents the user account in the DALA app.
 * contains user credentials and personal information.
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    /**
     * constructs a User with the specified account information.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     * @param username the username for login
     * @param password the password for authentication
     */
    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * gets the user's first name
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * gets the user's last name
     * @return the first name
     */
    public String getLastName() { return lastName; }

    /**
     * gets the user's email
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * gets the username
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * gets the password
     * @return the password
     */
    public String getPassword() { return password; }


    /**
     * sets the user's first name
     * @param firstName the first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * sets the user's last name
     * @param lastName the first name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * sets the email
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * sets the username
     * @param username the new username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * sets the password
     * @param password the new password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * returns a string representation of this user
     * @return a string containing username and full name
     */
    @Override
    public String toString() {
        return "User{username='" + username + "', name='" + firstName + " " + lastName + "'}";
    }
}