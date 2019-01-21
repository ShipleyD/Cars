package PaymentSimpleModel.Controller;

public class CardPayment extends Payment {
// A basic Card Payment object
    
    // constructor    
    public CardPayment() 
    {
	description = "Card Payment";
    }
        
    public double cost()
    {
	return cost;
    }
    
    public String getDescription()
    {
        return description;    
    }
}
