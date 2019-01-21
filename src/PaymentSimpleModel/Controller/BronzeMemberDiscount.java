package PaymentSimpleModel.Controller;



public class BronzeMemberDiscount extends AbstractPaymentDecorator {
	AbstractPayment payment;

	public BronzeMemberDiscount(AbstractPayment beverage) {
		this.payment = beverage;
	}

	public String getDescription() {
		return payment.getDescription() + ", Bronze Memeber Discount";
	}
        
        /** calculates and returns a discount of 10%
         * 
         * @return cost of payment minus discount 
         */
	public double cost() {
		return  payment.cost() - payment.cost()/40;
	}
}
