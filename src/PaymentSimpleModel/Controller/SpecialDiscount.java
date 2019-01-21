package PaymentSimpleModel.Controller;

import java.math.BigDecimal;


public class SpecialDiscount extends AbstractPaymentDecorator {
        // this variable gets set with e.g. Card Paymen object
	AbstractPayment payment;

        // constructor
        public SpecialDiscount(AbstractPayment beverage) {
		this.payment = beverage;
	}

        @Override
	public String getDescription() {
		return payment.getDescription() + ", Special Member Discount";
	}

        @Override
	public double cost() {
		return payment.cost() - payment.cost()/80;
	}
        }
