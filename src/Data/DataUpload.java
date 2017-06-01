/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classification.CzechStemmerAgressive;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;
import javafx.scene.control.TextArea;

/**
 *
 * @author Thomas
 */
public class DataUpload {

    private String stamp;
    private HashMap<String, Integer> filesCount = new HashMap<>();
    private final HashMap<String, HashMap<String, String>> mapOuter = new HashMap<>();
    private final CzechStemmerAgressive wordRoot = new CzechStemmerAgressive();
    private final HashMap mapInner = new HashMap<>();
    private HashMap recognized = new HashMap<>();
    private int x = 0;
    private String[] words;
    private String[] wordsNonDuplciated;

    private Boolean recognizedFile;
    private String nameFile;

    private String categoryFile;
    private String classifiedAs;
    public TextArea textArea;

    public DataUpload(File file) {
        this.recognizedFile = false;
        this.nameFile = file.getName();
    }

    public DataUpload() {

    }

    public void readAllFiles() throws Exception {
        /**
         * Načtení všech dokumentů ze složky a postupné zpracování
         */
        try (Stream<Path> filePathStream = Files.walk(Paths.get("doc_small"))) {
            filePathStream.forEach((Path filePath) -> {
                if (Files.isRegularFile(filePath)) {
                    File file = filePath.toFile();

                    readFile(file);

                }
            });
        }

    }

    public void readFile(File file) {

        /**
         * zjištění kategorie článku z názvu souboru
         */
        String[] category = file.toString().split(" |-|_|/|\\.");
        stamp = category[2];

        /**
         * Načtení dvou Hashmap a uložení informací
         */
        mapOuter.put(file.getName(), mapInner);
        // mapInner.put("Category", stamp);
        // mapInner.put("Recognized", false);

        /**
         * zpracování jednotlivých souborů
         */
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF8"))) {
            String str;
            while ((str = in.readLine()) != null) {

                wordsParser(str);

            }

        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        textArea.setText(textArea.getText() + file.getName() + "   " + stamp + "\n");
        textArea.setText(textArea.getText() + mapOuter.get(file.getName()) + "\n");

    }

    public void wordsParser(String str) {

        str = str.replaceAll("[^a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉůŮúÚ]", " ");
        str = wordRoot.stem(str);
        words = str.split("[[ ]*|[,]*|[\\.]*|[(]*|[)]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
        //words = wordRoot.stem(str);
        for (String word : words) {
            if (!mapInner.containsKey(word)) {
                mapInner.put(word, 1);

            } else {
                mapInner.put(word, (int) mapInner.get(word) + 1);
            }
        }
        System.out.println("Done" + x++);

    }

    public void readTextArea(String str) {

        // TO-DO 
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public Boolean getRecognized(String nazev) {
        recognized.clear();
        recognized = mapOuter.get(nazev);
        return (Boolean) recognized.get("Recognized");
    }

    public void setRecognizedFile(Boolean recognizedFile) {
        this.recognizedFile = recognizedFile;
    }

    public void setClassifiedAs(String classifiedAs) {
        this.classifiedAs = classifiedAs;
    }

    public HashMap<String, Integer> getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(HashMap<String, Integer> filesCount) {
        this.filesCount = filesCount;
    }

    public String getStamp() {
        return stamp;
    }

    public CzechStemmerAgressive getWordRoot() {
        return wordRoot;
    }

    public HashMap getRecognized() {
        return recognized;
    }

    public Boolean getRecognizedFile() {
        return recognizedFile;
    }

    public String getCategoryFile() {
        return categoryFile;
    }

    public String getClassifiedAs() {
        return classifiedAs;
    }
    
    

}
