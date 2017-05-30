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
public class DataTransfer implements Serializable{
        private Map<Path, TextRep> testovaciData = new HashMap<Path, TextRep>();
        private HashSet<String> testovaciSlova = new HashSet<String>();
        private ArrayList<TextRep> testovaciNN = new ArrayList<TextRep>();
        private Map<String,Categorie> testovaciBayes  = new HashMap<String,Categorie>();
        private ObservableMap<Path, TextRep> testDataGui = FXCollections.observableMap(testovaciData);
        
        
        private Map<Path, TextRep> trenovaciData = new HashMap<Path, TextRep>();
        private HashSet<String> trenovaciSlova = new HashSet<String>();
        private ArrayList<TextRep> trenovaciNN = new ArrayList<TextRep>();
        private Map<String,Categorie> trenovaciBayes  = new HashMap<String,Categorie>();
        private ObservableMap<Path, TextRep> trenDataGui = FXCollections.observableMap(trenovaciData);
        
        
    
}
