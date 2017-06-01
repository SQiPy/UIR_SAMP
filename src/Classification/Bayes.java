/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import Data.DataTransfer;
import DistancesAndFlags.Distance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thomas
 */
public class Bayes implements Classifier {

    private Data.DataTransfer dataTransfer;
    private double accuracy = 0;
    private DistancesAndFlags.Distance distance;
    private final ArrayList<TableTry> bayes;

    public Bayes(DataTransfer dataTransfer, Distance distance) {
        this.dataTransfer = dataTransfer;
        this.distance = distance;
        int categoryCount = dataTransfer.getTrainingBayes().size();

        ArrayList<TableTry> bayesList = new ArrayList<>();

        dataTransfer.getTrainingBayes().forEach((k, v) -> {
            double probability;
            double vyskyt;
            Map<String, Double> probabilities = new HashMap<>();
            probability = (double) v.getTextFilesCount() / categoryCount;
            probabilities.put("Categorie", probability);

            for (String slovo : dataTransfer.getTrainingWords()) {
                vyskyt = 0;
                if (v.getFilesCount().containsKey(slovo)) {
                    vyskyt = distance.probability(v, slovo);
                }
                probability = (vyskyt + 1)
                        / (dataTransfer.getTrainingWords().size() + v.getProbability(distance));
                probabilities.put(slovo, probability);
            }
            TableTry tabulka = new TableTry(v.getCategory(), probabilities);
            bayesList.add(tabulka);
        });
        this.bayes = bayesList;

    }

    @Override
    public void classify() {
        dataTransfer.getTestCategory().forEach(test -> {
            double pMax = -Double.MAX_VALUE;
            for (TableTry tab : bayes) {
                double probability=  0;
                probability = test.getFilesCount().keySet().stream().filter((k) -> 
                        (tab.getTable().containsKey(k))).map((k) -> 
                                Math.log(tab.getTable().get(k))).reduce(probability, (accumulator, _item) -> 
                                        accumulator + _item);
                probability += Math.log(tab.getTable().get("Categorie"));
                if (probability > pMax) {
                    pMax = probability;
                    test.getRecognized(tab.getCategory());
                }
            }
        });

        double correct = 0;
        double uncorrect = 0;
        double acc;

        for (Data.DataUpload testing : dataTransfer.getTestCategory()) {
            if (testing.getClassifiedAs().equals(testing.getCategoryFile())) {
                correct++;
                testing.setRecognizedFile(true);
            } else {
                uncorrect++;
            }
        }

        acc = correct / (correct + uncorrect);

        this.accuracy = acc * 100;
    }

    public DataTransfer getDataTransfer() {
        return dataTransfer;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDataTransfer(DataTransfer dataTransfer) {
        this.dataTransfer = dataTransfer;
    }
    
    
    public void setAccuraty(double accuraty) {
        this.accuracy = accuraty;
    }
    
    public void setDistance(Distance distance) {
		this.distance = distance;
	}

    
    
}
