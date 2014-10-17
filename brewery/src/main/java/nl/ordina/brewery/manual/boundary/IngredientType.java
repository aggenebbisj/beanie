/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.ordina.brewery.manual.boundary;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ingredient")
@XmlEnum
public enum IngredientType {
    
    @XmlEnumValue("water")
    WATER("water");
    
    private final String type;

    private IngredientType(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString(){
        return type;
    }
    
    
}
