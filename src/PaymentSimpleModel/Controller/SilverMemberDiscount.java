package PaymentSimpleModel.Controller;


public class SilverMemberDiscount extends AbstractPaymentDecorator {
	AbstractPayment payment;
 
	public SilverMemberDiscount(AbstractPayment payment) {
		this.payment = payment;
	}
 
	public String getDescription() {
		return payment.getDescription() + ", Silver Member Discount";
	}
        
         /** calculates and returns a discount of 10%
         * 
         * @return cost of payment minus a tenth 
         */
	public double cost() {
		return payment.cost() - payment.cost()/20;
	}
}
