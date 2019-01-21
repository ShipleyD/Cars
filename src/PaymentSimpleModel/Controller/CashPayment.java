package PaymentSimpleModel.Controller;

public class CashPayment extends Payment {

    private double amount; // variable for he amount paid by the user
  private String paidBy = "Paid by ?";
  
        
  public CashPayment() {
		description = "Cash Payment";
	}
 
   public String getDescription() {
        return description;    
    }
  // Constructor for this class - the sub classes call this by using 'super'
  public CashPayment(double cashTendered)
  {
      amount = cashTendered; // put value passed into the variable 'amount'
  }
  
  // simple getter for the variable 'amount'
  public double getAmount()
  {
      return amount;
  }
  
  // simple getter for the variable 'paidBy'
  public String getPaidBy()
  {
      return paidBy;
  }
  
  // method to calculate change
  // it receives the total of the bill and the money the customer has paid
  // into the two variables 'billTotal' and 'moneyTendered'
  public double getChange(double billTotal, double moneyTendered)
  {
      double change = 0.0; 
         
      // simple calculation to calculate the change due
      change = billTotal - moneyTendered;
      
      return change;
  }

public double cost() {
    return cost;
}  
}