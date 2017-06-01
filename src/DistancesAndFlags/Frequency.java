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
public class Frequency implements Distance {

    @Override
    public double distance(DataUpload st, DataUpload nd) {
        Set<String> keys = new HashSet<String>();
        keys.addAll(st.getFilesCount().keySet());
        keys.addAll(nd.getFilesCount().keySet());

        int appearence1 = 0;
        int appearence2 = 0;

        double distance = 0;
        int x = 0;

        for (String key : keys) {

            appearence1 = 0;
            appearence2 = 0;

            if (st.getFilesCount().containsKey(key)) {
                appearence1 = st.getFilesCount().get(key);
            }

            if (nd.getFilesCount().containsKey(key)) {
                appearence2 = nd.getFilesCount().get(key);
            }

            x = appearence1 - appearence2;

            distance += x * x;

        }

        return Math.sqrt(distance);
    }

    @Override
    public int probability(Categories category, String key) {
        return category.getFilesCount().get(key);
    }

    @Override
    public String toString() {
        return "frequency";
    }
}
