/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinanceOptions.Observer;

import java.util.*;

/**
 *
 * @author 32974205
 */
public class CarData implements Subject{
    private ArrayList observers;
    private double carPrice;
    private String carDescription;
    
    public CarData () {
        observers = new ArrayList();
    }
    
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    public void removeObserver (Observer o){
        int i = observers.indexOf(o);
        if (i >= 0){
            observers.remove(i);
        }
    }
    
    public void notifyObservers () {
        for (int i = 0; i < observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(carPrice, carDescription);
        }
    }
    
    public void dataChanged(){
        notifyObservers();
    }
    
    public void setData(double carPrice, String carDescription){
        this.carDescription = carDescription;
        this.carPrice = carPrice;
        
        dataChanged();
    }
    
//    public double getCarPrice(){
//        return carPrice;
//    }
//    
//    public String getCarDescription(){
//        return carDescription;
//    }            
}
