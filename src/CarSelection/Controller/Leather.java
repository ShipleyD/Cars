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
public class Leather extends AbIntAddOns {
    AbCar car;
    
    public Leather(AbCar car){
        this.car = car;
    }
    
    public String getDescription(){
        
        return car.getDescription() + "Leather, ";
    }
    
    public double cost(){
        
        return 150.00 + car.cost();
    }
    
}
