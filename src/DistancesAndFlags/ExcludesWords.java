/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistancesAndFlags;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thomas
 */
public class ExcludesWords {
    
    private String[] exclude = {
        "aby",
        "ač",
        "ačkoli",
        "ačkoliv",
        "ale",
        "aneb",
        "ani",
        "aniž",
        "ať",
        "během",
        "bez",
        "beze",
        "buď",
        "či",
        "dle",
        "do",
        "jménem",
        "k",
        "ke",
        "i",
        "kolem",
        "krom",
        "kromě",
        "ku",
        "kvůli",
        "mezi",
        "i když",
        "místo",
        "na",
        "nad",
        "jakkoli",
        "naproti",
        "jakkoliv",
        "o",
        "jen",
        "od",
        "ode",
        "ohledně",
        "okolo",
        "jenom",
        "po",
        "jenže",
        "jinak",
        "pode",
        "jestliže",
        "jestli",
        "kamkoli",
        "podlivá",
        "kdyby",
        "před",
        "kdybych",
        "přes",
        "kdybychom",
        "při",
        "pro",
        "když",
        "proti",
        "s",
        "se",
        "tak",
        "skrze",
        "stran",
        "u",
        "u příležitosti",
        "nebo",
        "v",
        "včetně",
        "ve",
        "vedle",
        "neboť",
        "vinou",
        "kéž",
        "vo",
        "vod",
        "vstříc",
        "vůči",
        "pak",
        "vz",
        "však",
        "potom",
        "z",
        "za",
        "protože",
        "že",
        "zkraje",
        "zda",
        "zpoza"
    };
    
    private final Set<String> filteredWords;

    public ExcludesWords() {
        filteredWords = new HashSet<>(Arrays.asList(exclude));
    }

    public Set<String> getFilteredWords() {
        return filteredWords;
    }
    
}
