package PaymentSimpleModel.Model;
/**
 * 
 * Simple class to simulate output to the model
 */
public class ModelPayment {

    double payment;
    
    /**
     * Whoever calls this, in this case the Model Controller passes
     * this object the payment total.
     * When this object is constructed it takes this payment value, places it
     * into the payment variable declared at the top, and then prints it to the screen 
     * @param payment 
     */
    public ModelPayment(double payment) {
        this.payment = payment;
    }
}