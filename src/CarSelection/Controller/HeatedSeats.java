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
public class HeatedSeats extends AbIntAddOns{
    
    AbCar car;
    
    public HeatedSeats(AbCar car){
        this.car = car;
    }
    
    public String getDescription(){
        
        return car.getDescription() + "Heated Seats, ";
    }
    
    public double cost(){
        
        return 500.00 + car.cost();
    }
    
}
