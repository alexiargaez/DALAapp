package edu.utsa.cs3443.dalaapp.model;

/**
 * represents an affirmation with its associated characteristics:
 * category, unique ID, and indicator if self-made
 */
public class Affirmation {
    private int id;
    private String quote;
    private String category;
    private boolean userMade;

    /**
     * constructs an Affirmation with the specified properties.
     *
     * @param id the unique ID for this affirmation
     * @param quote the text content of the affirmation
     * @param category the category this affirmation belongs to
     * @param userMade true if this affirmation was created by a user, false if it is not
     */
    public Affirmation(int id, String quote, String category, boolean userMade){
        this.id = id;
        this.quote = quote;
        this.category = category;
        this.userMade = userMade;
    }


    /**
     * returns the quote
     * @return the affirmation quote
     */
    @Override
    public String toString() { return quote; }


    /**
     * gets the ID of the affirmation
     * @return the affirmation ID
     */
    public int getId(){ return id; }

    /**
     * gets the quote
     * @return the affirmation quote
     */
    public String getQuote(){ return quote; }

    /**
     * gets the category
     * @return the category name
     */
    public String getCategory(){ return category; }

    /**
     * checks if the affirmation was created by a user
     * @return true of created by user, false otherwise
     */
    public boolean  isUserMade(){ return userMade; }


    /**
     * se†s the ID for the affirmation
     * @param id the ID value
     */
    public void setId(int id) { this.id = id; }

    /**
     * sets if the affirmation is made by user
     * @param userMade true if created by user, false otherwise
     */
    public void setUserMade(boolean userMade) {this.userMade = userMade; }

    /**
     * sets the category of the affirmation
     * @param category the category name
     */
    public void setCategory(String category) {this.category = category; }

    /**
     * sets the quote for the affirmation
     * @param quote the new quote
     */
    public void setQuote(String quote) {this.quote = quote; }
}
