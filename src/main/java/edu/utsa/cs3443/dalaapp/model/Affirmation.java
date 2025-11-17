package edu.utsa.cs3443.dalaapp.model;

public class Affirmation {
    private int id;
    private String quote;
    private String category;
    private boolean userMade;

    public Affirmation(int id, String quote, String category, boolean userMade){
        this.id = id;
        this.quote = quote;
        this.category = category;
        this.userMade = userMade;
    }

    @Override
    public String toString() { return quote; }


/* Getters */
    public int getId(){ return id; }

    public String getQuote(){ return quote; }

    public String getCategory(){ return category; }

    public boolean  isUserMade(){ return userMade; }

/* Setters */
    public void setId(int id) { this.id = id; }

    public void setUserMade(boolean userMade) { this.userMade = userMade; }

    public void setCategory(String category) { this.category = category; }

    public void setQuote(String quote) { this.quote = quote; }
}
