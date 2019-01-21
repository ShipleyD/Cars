package PaymentSimpleModel.Controller;

public class GoldMemberDiscount extends AbstractPaymentDecorator {
	AbstractPayment payment;

	public GoldMemberDiscount(AbstractPayment payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return payment.getDescription() + ", Gold Member Discount";
	}

        
	public double cost() {
		return payment.cost() - payment.cost()/10;
	}
}