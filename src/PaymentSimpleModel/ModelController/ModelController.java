package PaymentSimpleModel.ModelController;

import PaymentSimpleModel.Model.ModelPayment;

/**
 * This gets called by the form and passes the data (the payment) to the
 * Model Payment object
 * 
 */
public class ModelController {

    // object variable to hold the new object created in the constructor
    ModelPayment mp;
    
    double payment;
    
    
    public ModelController(double payment) {    
    {
        // get the payment from the form
        this.payment = payment;
        // create a new ModelPayment object and give it the payment
        mp = new ModelPayment(payment);
    }
    }
}
