/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSelection.Controller;

/**
 *
 * @author 32974205
 */
public abstract class AbCar {
    
    String description = "Unknown Car";
    String colour = "Unknown Colour";
    
     public String getDescription(){
        return description;
    }
    
    public String getColour(String colour){
        return colour;
    }
     
    public abstract double cost();
    
}



