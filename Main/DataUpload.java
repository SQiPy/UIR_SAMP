/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classification.CzechStemmerAgressive;
import GUI.Gui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import javafx.scene.control.TextArea;

/**
 *
 * @author Thomas
 */
public class DataUpload {

    private static String stamp;

    private static HashMap<String, HashMap<String, String>> mapOuter = new HashMap<>();
    private static CzechStemmerAgressive wordRoot = new CzechStemmerAgressive();
    private static HashMap mapInner = new HashMap<>();
    private static int x = 0;
    private static String[] words;
    private static String[] wordsNonDuplciated;

    public static void readFile() throws Exception {
        
        /** Načtení všech dokumentů ze složky a postupné zpracování */
        try (Stream<Path> filePathStream = Files.walk(Paths.get("doc_small"))) {
            filePathStream.forEach((Path filePath) -> {
                if (Files.isRegularFile(filePath)) {
                    File file = filePath.toFile();
                    
                    /** zjištění kategorie článku z názvu souboru */
                    String[] category = file.toString().split(" |-|_|/|\\.");
                    stamp = category[2];

                    Gui.textArea.setText(Gui.textArea.getText() + filePath + /*"   " + stamp +*/ "\n");
                    
                    /** Načtení dvou Hashmap a uložení informací */
                    mapOuter.put(filePath.toString(), mapInner);
                    mapInner.put("Category", stamp);
                    mapInner.put("Recognized", false);

                    /** zpracování jednotlivých souborů */
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file), "UTF8"))) {
                        String str;
                        while ((str = in.readLine()) != null) {
 
                            str = str.replaceAll("[^a-zA-ZěĚšŠčČřŘžŽýÝáÁíÍéÉůŮúÚ]", " ");
                            str = wordRoot.stem(str);
                            String nef = "ahoj";
                            nef = "ne";
                            System.out.println(nef);
                            words = str.split("[[ ]*|[,]*|[\\.]*|[(]*|[)]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
                            //words = wordRoot.stem(str);
                            for (String word : words) {
                                if (!mapInner.containsKey(word)) {
                                    mapInner.put(word, 1);
                                    
                                } else {
                                    mapInner.put(word, (int)mapInner.get(word) + 1);
                                }
                            }
                            System.out.println("Done" + x++);

                        }

                    } catch (UnsupportedEncodingException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    Gui.textArea.setText(Gui.textArea.getText() + mapOuter.get(filePath.toString()) + "\n");

                }
            });
        }

    }

    public static String[] nonDuplicatedArrayString(String[] words) {
        Arrays.sort(words);
        Set<String> set = new LinkedHashSet<>(Arrays.asList(words));
        String[] result = new String[set.size()];
        set.toArray(result);
        return set.toArray(result);
    }

}
