/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Thomas
 */
public class Categories implements Serializable {

    private HashMap<String, Integer> filesCount = new HashMap<>();

    private int textFilesCount;
    private int probability;
    private String category;

    public Categories(String category) {
        this.textFilesCount = 0;
        this.probability = 0;
        this.category = category;
    }

    public void add(Data.DataUpload text) {
        text.getFilesCount().forEach((k, v) -> filesCount.merge(k, v, (v1, v2) -> v1 + v2));
        this.textFilesCount++;
    }

    public void calculate(DistancesAndFlags.Distance distance) {
        filesCount.forEach((k, v) -> probability += distance.probability(this, k));
    }

    public HashMap<String, Integer> getFilesCount() {
        return filesCount;
    }

    public int getTextFilesCount() {
        return textFilesCount;
    }

    public int getProbability(DistancesAndFlags.Distance distance) {
        if (probability == 0) {
            calculate(distance);
        }
        return probability;
    }

    public String getCategory() {
        return category;
    }

    public void setfilesCount(HashMap<String, Integer> filesCount) {
        this.filesCount = filesCount;
    }

    public void setTextFilesCount(int textFilesCount) {
        this.textFilesCount = textFilesCount;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
