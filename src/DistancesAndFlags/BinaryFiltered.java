/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistancesAndFlags;

import Classification.Categories;
import Data.DataUpload;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thomas
 */
public class BinaryFiltered implements Distance {

    ExcludesWords excludes = new ExcludesWords();

    @Override
    public double distance(DataUpload st, DataUpload nd) {
        Set<String> keys = new HashSet<>();
        keys.addAll(st.getFilesCount().keySet());
        keys.addAll(nd.getFilesCount().keySet());

        int appearence1;
        int appearence2;
        int x;
        
        double distance = 0;

        for (String key : keys) {

            if (!excludes.getFilteredWords().contains(key)) {
                appearence1 = 0;
                appearence2 = 0;

                if (st.getFilesCount().containsKey(key)) {
                    appearence1 = 1;
                }

                if (nd.getFilesCount().containsKey(key)) {
                    appearence2 = 1;
                }

                x = appearence1 - appearence2;

                distance += x * x;
            }

        }

        return Math.sqrt(distance);
    }

    @Override
    public int probability(Categories category, String key) {

        if (excludes.getFilteredWords().contains(key)) {
            return 0;
        }
        return 1;

    }

    @Override
    public String toString() {
        return "filteredbinary";
    }

}
