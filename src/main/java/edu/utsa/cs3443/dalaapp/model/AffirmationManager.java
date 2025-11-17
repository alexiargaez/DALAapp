package edu.utsa.cs3443.dalaapp.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

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





}

