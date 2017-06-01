/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classification.CzechStemmerAgressive;
import GUI.Gui;
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

/**
 *
 * @author Thomas
 */
public class DataUpload {

    private static String stamp;

    private static final HashMap<String, HashMap<String, String>> mapOuter = new HashMap<>();
    private static final CzechStemmerAgressive wordRoot = new CzechStemmerAgressive();
    private static final HashMap mapInner = new HashMap<>();
    private static HashMap recognized = new HashMap<>();
    private static int x = 0;
    private static String[] words;
    private static String[] wordsNonDuplciated;

    public static void readAllFiles() throws Exception {
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

    public static void readFile(File file) {

        /**
         * zjištění kategorie článku z názvu souboru
         */
        String[] category = file.toString().split(" |-|_|/|\\.");
        stamp = category[2];

        Gui.textArea.setText(Gui.textArea.getText() + file.getName() + "   " + stamp + "\n");

        /**
         * Načtení dvou Hashmap a uložení informací
         */
        mapOuter.put(file.getName(), mapInner);
        System.out.println("cesta - " +file.getName());
        mapInner.put("Category", stamp);
        mapInner.put("Recognized", false);

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

        Gui.textArea.setText(Gui.textArea.getText() + mapOuter.get(file.getName()) + "\n");

    }

    public static void wordsParser(String str) {

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

    public static void readTextArea(String str) {

        // TO-DO 
    }

    public static Boolean getRecognized(String nazev){
        recognized.clear();
        recognized = mapOuter.get(nazev);
        return (Boolean)recognized.get("Recognized");
    }
}
