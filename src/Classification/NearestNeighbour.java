/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import Data.DataTransfer;
import DistancesAndFlags.Distance;

/**
 *
 * @author Thomas
 */
public class NearestNeighbour implements Classifier {

    private Data.DataTransfer dataTransfer;
    private double accuracy = 0;
    private DistancesAndFlags.Distance distance;

    public NearestNeighbour(DataTransfer dataTransfer, Distance distance) {
        this.dataTransfer = dataTransfer;
        this.distance = distance;
    }

    @Override
    public void classify() {
        double dRozdil;
        double rozdil;

        for (Data.DataUpload testovaci : dataTransfer.getTestCategory()) {
            dRozdil = 0;
            rozdil = Double.MAX_VALUE;
            for (Data.DataUpload trenovaci : dataTransfer.getTrainingCategory()) {
                dRozdil = distance.distance(testovaci, trenovaci);
                if (dRozdil < rozdil) {
                    rozdil = dRozdil;
                    testovaci.setClassifiedAs(trenovaci.getCategoryFile());
                }
            }
        }

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

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

}
