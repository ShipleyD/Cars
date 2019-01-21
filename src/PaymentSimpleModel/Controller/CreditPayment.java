package PaymentSimpleModel.Controller;

// see comments in CArd Payment class
public class CreditPayment extends Payment {
	public CreditPayment() {
		description = "Credit Payment";
	}
 
        
        public String getDescription() {
            return description;
        }

        public double cost() {
            return cost;
	}
}