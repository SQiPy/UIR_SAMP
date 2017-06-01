/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classification.Categories;
import Data.DataUpload;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *
 * @author Thomas
 */
public class DataTransfer implements Serializable {

    //  private NN soused;
    //  private Bayes bayes
    /**
     * Testovací proměnné
     */
    private Map<Path, DataUpload> testData = new HashMap<>();
    private HashSet<String> testWords = new HashSet<>();
    private ArrayList<DataUpload> testNN = new ArrayList<>();
    private Map<String, Categories> testBayes = new HashMap<>();
    private ObservableMap<Path, DataUpload> testDataGui = FXCollections.observableMap(testData);

    /**
     * Trénovací proměnné
     */
    private Map<Path, DataUpload> trainingData = new HashMap<>();
    private HashSet<String> trainingWords = new HashSet<>();
    private ArrayList<DataUpload> trainingNN = new ArrayList<>();
    private Map<String, Categories> trainingBayes = new HashMap<>();
    private ObservableMap<Path, DataUpload> trainingDataGui = FXCollections.observableMap(trainingData);

    public void testDataReset() {
        Set<Path> keys = testData.keySet();
        keys.stream().map((key) -> {
            testData.get(key).setRecognizedFile(false);
            return key;
        }).forEach((key) -> {
            testData.get(key).setClassifiedAs(null);
        });
    }

    public DataTransfer(DataTransferManipulation d) {
        d.testData.forEach((k, v) -> this.testData.put(Paths.get(k), v));
        d.trainingData.forEach((k, v) -> this.trainingData.put(Paths.get(k), v));
        
        this.testBayes = d.testBayes;
        this.testDataGui = FXCollections.observableMap(testData);
        this.testNN = d.testNN;
        this.testWords = d.testWords;
        
        this.trainingBayes = d.trainingBayes;
        this.trainingDataGui = FXCollections.observableMap(trainingData);
        this.trainingNN = d.trainingNN;
        this.trainingWords = d.trainingWords;
        
        // TODO

    }

    public DataTransfer() {
    }
    

    public Map<Path, DataUpload> getTestData() {
        return testData;
    }

    public HashSet<String> getTestWords() {
        return testWords;
    }

    public ArrayList<DataUpload> getTestNN() {
        return testNN;
    }

    public Map<String, Categories> getTestBayes() {
        return testBayes;
    }

    public ObservableMap<Path, DataUpload> getTestDataGui() {
        return testDataGui;
    }

    public Map<Path, DataUpload> getTrainingData() {
        return trainingData;
    }

    public HashSet<String> getTrainingWords() {
        return trainingWords;
    }

    public ArrayList<DataUpload> getTrainingNN() {
        return trainingNN;
    }

    public Map<String, Categories> getTrainingBayes() {
        return trainingBayes;
    }

    public ObservableMap<Path, DataUpload> getTrainingDataGui() {
        return trainingDataGui;
    }

    public void setTestData(Map<Path, DataUpload> testData) {
        this.testData = testData;
    }

    public void setTestWords(HashSet<String> testWords) {
        this.testWords = testWords;
    }
    
     public ArrayList<DataUpload> getTestCategory() {
		return testNN;
	}

    public void setTestCategory(ArrayList<DataUpload> testNN) {
        this.testNN = testNN;
    }
    
    public ArrayList<DataUpload> getTrainingCategory() {
		return trainingNN;
	}
    

    public void setTrainingCategory(ArrayList<DataUpload> trainingNN) {
        this.trainingNN = trainingNN;
    }

    public void setTrainingBayes(Map<String, Categories> trainingBayes) {
        this.trainingBayes = trainingBayes;
    }

    public void setTestBayes(Map<String, Categories> testBayes) {
        this.testBayes = testBayes;
    }

    public void setTrainingData(Map<Path, DataUpload> trainingData) {
        this.trainingData = trainingData;
    }

    public void setTrainingWords(HashSet<String> trainingWords) {
        this.trainingWords = trainingWords;
    }

}
