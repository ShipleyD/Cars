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
public class Alloys extends AbExtAddOns{
    
   AbCar car;
    
    public Alloys(AbCar car){
        this.car = car;
    }
    
    public String getDescription(){
        
        return car.getDescription() + "Alloys, ";
    }
    
    public double cost(){
        
        return 190.00 + car.cost();
    }
    
}
