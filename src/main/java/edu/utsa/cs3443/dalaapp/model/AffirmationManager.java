package edu.utsa.cs3443.dalaapp.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class AffirmationManager {
    private static final ArrayList<Affirmation> allAffirmations = new ArrayList<>();
    private static final Random rand = new Random();
    private static boolean populated = false;

    public static synchronized void loadAffirmations(String resourcePath) {
        if (populated) return;
        allAffirmations.clear();

        try (InputStream in = AffirmationManager.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                System.out.println("CSV not found: " + resourcePath);
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String line;

                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] p = parseCsv(line);
                    if (p.length < 4) continue;

                    int id = tryInt(p[0], -1);
                    String quote = p[1].trim();
                    String cat   = p[2].trim();
                    boolean user = parseBool(p[3]);

                    if (id >= 0 && !quote.isEmpty() && !cat.isEmpty()) {
                        allAffirmations.add(new Affirmation(id, quote, cat, user));
                    }
                }
            }
            populated = true;
        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }


}

