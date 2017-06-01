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
public class DataTransfer implements Serializable{
    
            /** Testovací proměnné */
        private Map<Path, DataUpload> testovaciData = new HashMap<Path, DataUpload>();
        private HashSet<String> testovaciSlova = new HashSet<String>();
        private ArrayList<DataUpload> testovaciNN = new ArrayList<DataUpload>();
        private Map<String,Categories> testovaciBayes  = new HashMap<String,Categories>();
        private ObservableMap<Path, DataUpload> testDataGui = FXCollections.observableMap(testovaciData);
        
            /** Trénovací proměnné */
        private Map<Path, DataUpload> trenovaciData = new HashMap<Path, DataUpload>();
        private HashSet<String> trenovaciSlova = new HashSet<String>();
        private ArrayList<DataUpload> trenovaciNN = new ArrayList<DataUpload>();
        private Map<String,Categories> trenovaciBayes  = new HashMap<String,Categories>();
        private ObservableMap<Path, DataUpload> trenDataGui = FXCollections.observableMap(trenovaciData);
        
        
    
}
