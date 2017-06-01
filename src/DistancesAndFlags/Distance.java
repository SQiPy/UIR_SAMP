/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistancesAndFlags;

import Classification.Categories;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public interface Distance extends Serializable {
    
    @Override
    public String toString();

    public double distance(Data.DataUpload st, Data.DataUpload nd);

    public int probability (Categories category, String key);

}
