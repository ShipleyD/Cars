/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinanceOptions.Observer;

import FinanceOptions.View.FraFinance;
import PaymentSimpleModel.View.UIPaymentSimpleModel;
/**
 *
 * @author 32974205
 */
public class D12Months implements Observer, DisplayElement {
        
    
    private int remainingMonths;
    private double avgMonthlyPayment;
    private double totalPaid;
    private double leftToPay;
    private double paidPerYear;
    private double monthlyCost;
    private int numPayments;
    private double interestRate;
    private double carPrice;
    private String carDescription;
    private double carPriceTotal;
    private Subject carData;
    private FraFinance newForm;
    private UIPaymentSimpleModel paymentScreen;
    private String paymentPlan;
    
    public D12Months (Subject carData, UIPaymentSimpleModel PaymentScreen){
        this.carData = carData;
        this.paymentScreen = PaymentScreen;
        paymentPlan = "12 Months";
        carData.registerObserver(this);
    }
    
    public void update(double CarPrice, String CarDescription){
        this.carDescription = CarDescription;
        this.carPrice = CarPrice;
        
        leftToPay = carPrice;
        numPayments = 12;
        interestRate = 19.99;
        remainingMonths = numPayments;
        
        for (int i = 0; i < 1; i++){
            carPriceTotal = leftToPay + ((leftToPay/100)*interestRate);
            monthlyCost = carPriceTotal/remainingMonths;
            paidPerYear = monthlyCost * 12;
            leftToPay = carPriceTotal - paidPerYear; 
            totalPaid += paidPerYear;
            remainingMonths -= 12;  
        }
        
        avgMonthlyPayment = totalPaid/numPayments;
    }
    public void display(){
        newForm = new FraFinance(carPrice, carDescription, interestRate, avgMonthlyPayment, totalPaid, numPayments, paymentScreen, paymentPlan);
        newForm.setVisible(true);
    }
    

}
