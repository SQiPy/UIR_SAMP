/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Thomas
 */
public class TableTry implements Serializable {

    private final Map<String, Double> table;
    private final String category;

    TableTry(String category, Map<String, Double> table) {
        this.table = table;
        this.category = category;
    }

    public Map<String, Double> getTable() {
        return table;
    }

    public String getCategory() {
        return category;
    }

}
