package org.skypro.skyshop.model.search;

import java.util.Comparator;

public class sortedTitleComparator implements Comparator<Searchable> {
    public int compare(Searchable o1, Searchable o2) {
        int lengthCompare = Integer.compare(o2.getName().length(), o1.getName().length());
        if (lengthCompare != 0) {
            return lengthCompare;
        }
        return o1.getName().compareTo(o2.getName());
    }
}
