/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classification.Categories;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Thomas
 */
public class DataTransferManipulation implements Serializable {

    private static final String EXTENSION = ".datT";

    /**
     * Testovací proměnné
     */
    public Map<String, DataUpload> testData = new HashMap<>();
    public HashSet<String> testWords = new HashSet<>();
    public ArrayList<DataUpload> testNN = new ArrayList<>();
    public Map<String, Categories> testBayes = new HashMap<>();

    /**
     * Trénovací proměnné
     */
    public Map<String, DataUpload> trainingData = new HashMap<>();
    public HashSet<String> trainingWords = new HashSet<>();
    public ArrayList<DataUpload> trainingNN = new ArrayList<>();
    public Map<String, Categories> trainingBayes = new HashMap<>();

    public DataTransferManipulation(DataTransfer d) {

        d.getTestData().forEach((k, v) -> this.testData.put(k.toString(), v));
        d.getTrainingData().forEach((k, v) -> this.trainingData.put(k.toString(), v));

        this.testBayes = d.getTestBayes();
        this.testNN = d.getTestNN();
        this.testWords = d.getTestWords();

        this.trainingBayes = d.getTrainingBayes();
        this.trainingNN = d.getTrainingNN();
        this.trainingWords = d.getTrainingWords();

    }

    public static DataTransfer load(String saved) throws IOException, ClassNotFoundException {
        String modelName = saved;
        if (!modelName.endsWith(EXTENSION)) {
            modelName = modelName + EXTENSION;
        }

        File file = new File(modelName);
        BufferedInputStream buffStream;
        ObjectInputStream objStream;
        DataTransferManipulation tmp;

        try (FileInputStream fileStream = new FileInputStream(file)) {
            buffStream = new BufferedInputStream(fileStream);
            objStream = new ObjectInputStream(buffStream);
            tmp = (DataTransferManipulation) objStream.readObject();
        }

        buffStream.close();
        objStream.close();

        DataTransfer dataTransfer = new DataTransfer(tmp);

        return dataTransfer;
    }

    public static void save(File model, DataTransferManipulation dataTM) throws IOException {

        BufferedOutputStream buffStream;
        ObjectOutputStream objStream;
        
        try (FileOutputStream fileStream = new FileOutputStream(model)) {
            buffStream = new BufferedOutputStream(fileStream);
            objStream = new ObjectOutputStream(buffStream);
            objStream.writeObject(dataTM);
        }
        
        buffStream.close();
        objStream.close();

    }

}
