package PaymentSimpleModel.Controller;

// The interface for the Discounts

// We need to create objects of this type so that we can create 'more than basic'
// Payments
// e.g. A basic payment with (plus) discounts (Gold, etc)

public abstract class AbstractPaymentDecorator extends AbstractPayment {
        @Override
	public abstract String getDescription();
}
