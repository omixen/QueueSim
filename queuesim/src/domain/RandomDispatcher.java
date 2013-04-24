package domain;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 */
public class RandomDispatcher extends Dispatcher {
    private int maxCount = 10;
    private int minCount = 0;

    public Customer[] dispatch(Long time) {

        ArrayList<Customer> customers = new ArrayList<Customer>();
        // Random num between 1 and 10
        int rand = minCount + (int)(Math.random() * ((maxCount - minCount) + 1));
        int count = (int)(time % rand);

        //create number of customers based on the customer type distribution
        for(Iterator<CustomerType> iType=this.getCustomerTypes().iterator();iType.hasNext();) {
            CustomerType type = iType.next();
            int typeCount = (int)type.getDistribution()*count;
            for(int i=0;i<typeCount;i++) {
                customers.add(new Customer(type, time));
            }
        }

        return customers.toArray(new Customer[customers.size()]);
    }
}
