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
public class Categories implements Serializable {

    private HashMap<String, Integer> cetnost = new HashMap<>();

    private int pocetTextu;
    private int pravdepodobnost;
    private String category;

    public Categories(String category) {
        this.pocetTextu = 0;
        this.pravdepodobnost = 0;
        this.category = category;
    }

    public void pridej(Data.DataUpload text) {
        text.getCetnost().forEach((k, v) -> cetnost.merge(k, v, (v1, v2) -> v1 + v2));
        this.pocetTextu++;
    }

    public void vypocitej(Vzdalenost vz) {
        cetnost.forEach((k, v) -> pravdepodobnost += vz.pravdepodobnost(this, k));
    }

    public HashMap<String, Integer> getCetnost() {
        return cetnost;
    }

    public int getPocetTextu() {
        return pocetTextu;
    }

    public int getPravdepodobnost(Vzdalenost vz) {
        if (pravdepodobnost == 0) {
            vypocitej(vz);
        }
        return pravdepodobnost;
    }

    public String getCategory() {
        return category;
    }

    public void setCetnost(HashMap<String, Integer> cetnost) {
        this.cetnost = cetnost;
    }

    public void setPocetTextu(int pocetTextu) {
        this.pocetTextu = pocetTextu;
    }

    public void setPravdepodobnost(int pravdepodobnost) {
        this.pravdepodobnost = pravdepodobnost;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
