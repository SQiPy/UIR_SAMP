/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author Thomas
 */
public class TextRepresentation implements Serializable {

    private HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();
    private CzechStemmerAgressive wordRoot = new CzechStemmerAgressive();
    private final Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
    private String name;
    private String category;
    private String textDetermination;
    private boolean classificated;

    public TextRepresentation(Path file) {
        this.classificated = false;
        this.name = file.getFileName().toString();
        
        String[] category = name.split("|-|_|/|\\.");
        this.category = category[1];
        //read(file);
    }
    
    
    
    
    

}
