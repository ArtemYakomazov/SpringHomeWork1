package org.skypro.skyshop.model.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchableItems;
    private int count = 0;


    public SearchEngine() {
        searchableItems = new HashSet<>();
    }

    public void add(Searchable item, UUID id) {
        searchableItems.add(item);
        count++;
    }

    public Set<Searchable> search(String term) {
        return searchableItems.stream()
                .filter(s -> s.searchTerm().contains(term))
                .limit(5)
                .collect(Collectors.toCollection(()->new TreeSet<>(new sortedTitleComparator())));
    }

    public void findBestMatch(String search) throws BestResultsNotFound {
        Searchable bestMatch = null;
        int found = 0;

        for (Searchable searchable : searchableItems) {
            if (searchable != null) {
                String str = searchable.searchTerm().toLowerCase();
                int score = 0;
                int index = 0;
                while ((index = str.indexOf(search.toLowerCase(), index)) != -1) {
                    score++;
                    index++;
                }

                if (score > found) {
                    found = count;
                    bestMatch = searchable;
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultsNotFound(search);
        }
        System.out.println(bestMatch.getStringRepresentation());
    }

    @Override
    public String toString() {
        return "Статьи" + searchableItems;
    }
}
