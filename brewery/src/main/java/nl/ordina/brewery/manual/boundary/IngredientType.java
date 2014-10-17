/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.ordina.brewery.manual.boundary;

/**
 *
 * @author kol20242
 */
public enum IngredientType {
    
    water("water");
    private final String type;

    private IngredientType(final String type) {
        this.type = type;
    }
    
    public String toString(){
        return type;
    }
    
    
}
