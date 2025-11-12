package edu.utsa.cs3443.dalaapp.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class AffirmationManager {

    // All affirmations
    private final ArrayList<Affirmation> allAffirmations = new ArrayList<>();

    // Per-category lists (kept human-readable to match your UI)
    private final ArrayList<Affirmation> selfLoveAffirmations = new ArrayList<>();
    private final ArrayList<Affirmation> funnyAffirmations = new ArrayList<>();
    private final ArrayList<Affirmation> motivationalAffirmations = new ArrayList<>();

    // Also a map for easy lookup by category name (e.g., "Self-Love")
    private final Map<String, ArrayList<Affirmation>> affirmationsByCategory = new HashMap<>();

    private final Random random = new Random();


    public void loadAffirmations(String resourcePath) {
        // 1) clear everything
        allAffirmations.clear();
        selfLoveAffirmations.clear();
        funnyAffirmations.clear();
        motivationalAffirmations.clear();
        affirmationsByCategory.clear();

        // 2) open the CSV from resources
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            if (in == null) {
                System.out.println("CSV not found: ");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

                // 3) skip the header line
                br.readLine();

                // 4) read each line, parse, and add
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = parseCsvLine(line); // handles quotes
                    if (parts.length < 4) continue;

                    int id = safeInt(parts[0], -1);
                    String quote    = parts[1].trim();
                    String category = parts[2].trim();      // "Self-Love" | "Funny" | "Motivational"
                    boolean userMade = parseBool(parts[3]); // TRUE/FALSE

                    if (id < 0 || quote.isEmpty() || category.isEmpty()) continue;

                    Affirmation a = new Affirmation(id, quote, category, userMade);

                    // add to master list
                    allAffirmations.add(a);

                    // add to per-category list (human names)
                    switch (category) {
                        case "Self-Love"     -> selfLoveAffirmations.add(a);
                        case "Funny"         -> funnyAffirmations.add(a);
                        case "Motivational"  -> motivationalAffirmations.add(a);
                        default -> { /* ignore unknown categories for now */ }
                    }

                    // add to map
                    affirmationsByCategory
                            .computeIfAbsent(category, k -> new ArrayList<>())
                            .add(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }

    // -------------------- Public getters (simple) --------------------

    public ArrayList<Affirmation> getAllAffirmations() { return allAffirmations; }
    public ArrayList<Affirmation> getSelfLoveAffirmations() { return selfLoveAffirmations; }
    public ArrayList<Affirmation> getFunnyAffirmations() { return funnyAffirmations; }
    public ArrayList<Affirmation> getMotivationalAffirmations() { return motivationalAffirmations; }

    public ArrayList<Affirmation> getAffirmationsByCategory(String category) {
        return affirmationsByCategory.getOrDefault(category, new ArrayList<>());
    }

    public List<String> getCategories() {
        return new ArrayList<>(affirmationsByCategory.keySet());
    }

    // -------------------- Random helpers (beginner-friendly) --------------------

    /** Random from all */
    public Affirmation getRandomAffirmation() {
        if (allAffirmations.isEmpty()) return null;
        return allAffirmations.get(random.nextInt(allAffirmations.size()));
    }

    /** Random from a specific category, e.g., "Self-Love" */
    public Affirmation getRandomAffirmationByCategory(String category) {
        ArrayList<Affirmation> list = affirmationsByCategory.get(category);
        if (list == null || list.isEmpty()) return null;
        return list.get(random.nextInt(list.size()));
    }

    // -------------------- Add new (in-memory only) --------------------

    /** Adds a user-made affirmation in memory (not saving to CSV). */
    public void addUserAffirmation(String quote, String category) {
        int newId = allAffirmations.stream().mapToInt(Affirmation::getId).max().orElse(0) + 1;
        Affirmation a = new Affirmation(newId, quote, category, true);

        allAffirmations.add(a);
        switch (category) {
            case "Self-Love"     -> selfLoveAffirmations.add(a);
            case "Funny"         -> funnyAffirmations.add(a);
            case "Motivational"  -> motivationalAffirmations.add(a);
        }
        affirmationsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(a);
    }

    // -------------------- Small parsing helpers --------------------

    /** Handles commas inside quotes: 1,"I, me, we",Self-Love,TRUE */
    private String[] parseCsvLine(String line) {
        ArrayList<String> out = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                out.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        out.add(sb.toString());
        return out.toArray(new String[0]);
    }

    private int safeInt(String s, int dflt) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return dflt; }
    }

    private boolean parseBool(String s) {
        s = s.trim().toLowerCase();
        return s.equals("true") || s.equals("1") || s.equals("yes");
    }
}

