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
public class Black extends AbColour{
    
    AbCar car;
    
    public Black(AbCar car){
        this.car = car;
    }
    
    public String getColour(String colour){
        return colour;
    }
    
    public String getDescription(){
        
        return car.getDescription();
    }
    
    public double cost(){
        
        return 00.00 + car.cost();
    }
    
}
