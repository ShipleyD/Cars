package PaymentSimpleModel.Controller;
// We need to create objects of this type so that we can create 'basic' Payments
// The basic Payments with no discounts

// e.g. Cash, Card, Credit
// A basic Payment object e.g Card is simply
// description = "Card Payment", the cost gets set by the text box on the form 

public abstract class AbstractPayment {
        
        // put a string into the String variable - this will get over-written
        // when we create the object
	protected String description = "Payment Type Unknown";
        
        // hold the payment from the text box
        protected double cost = 0.0;
        
        // variable to hold the payment
        public double thePayment;
        
        // The description of a basic beverage - over writes the above variable
        // when we create e.g. an Card Payment object
	public abstract String getDescription();
        
        
        // gets and sets for the variable declared above
        public double getThePayment() {
            return cost;
        }
        
        public void setThePayment(double thePayment) {
            cost = thePayment;
        }
 
        
        // The cost abstract method that all payment types need to implement
	public abstract double cost();
}