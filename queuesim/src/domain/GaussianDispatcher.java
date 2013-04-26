package domain;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Based on Gaussion Distribution
 */
public class GaussianDispatcher extends Dispatcher {
    private double a = 10;
    private double b = 0;
    private double c = 0;

    public GaussianDispatcher(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Customer[] dispatch(Long time) {
        double timeNumber = time/1000;
        ArrayList<Customer> customers = new ArrayList<Customer>();
        int number = 0;

        Enumeration keys = this.getCustomerTypes().keys();
        while(keys.hasMoreElements()) {
            String type = keys.nextElement().toString();
            CustomerType customerType = this.getCustomerTypes().get(type);
            //gaussion function
            number = (int)(a*(Math.pow(Math.E, -(Math.pow(timeNumber-b, 2)/(2*Math.pow(c, 2))))));

        }
        return customers.toArray(new Customer[customers.size()]);
    }
}
