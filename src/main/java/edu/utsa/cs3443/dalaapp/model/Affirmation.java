package edu.utsa.cs3443.dalaapp.model;

public class Affirmation {

    //attributes
    private final int id;
    private final String quote;
    private final String category;
    private final boolean userMade;

    public Affirmation(int id, String quote, String category, boolean userMade){
        this.id = id;
        this.quote = quote;
        this.category = category;
        this.userMade = userMade;
    }

    public int getId(){

        return id;
    }
    public String getQuote(){

        return quote;
    }
    public String getCategory(){

        return category;
    }
    public boolean  isUserMade(){

        return userMade;
    }

    @Override
    public String toString() {

        return quote;
    }
}
