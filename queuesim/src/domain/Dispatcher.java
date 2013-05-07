package domain;

import java.util.*;

/**
 *  Abstract Class Dispatcher
 *  Base class for all dispatchers
 */
public abstract class Dispatcher {
    private ArrayList<Queue> queues;
    private Hashtable<String, CustomerType> customerTypes;

    public ArrayList<Queue> getQueues() {
        return this.queues;
    }

    public void setQueues(ArrayList<Queue> queues) {
        this.queues = queues;
    }

    public void addQueue(Queue queue) {
        if(this.queues != null) {
            this.queues.add(queue);
        }
    }

    public Hashtable<String, CustomerType> getCustomerTypes() {
        return this.customerTypes;
    }

    public void setCustomerTypes(Hashtable<String, CustomerType> customerTypes) {
        this.customerTypes = customerTypes;
    }

    /*
     *  Helper functions
     */

    public void dispatchCustomers(long tick) {
        //create customers based on the time
        Customer[] customers = this.dispatch(tick);
        //assign customers to queues
        if (customers != null && customers.length > 0) {
            for(int i=0;i<customers.length;i++) {
                getQueue(customers[i].getType()).enqueue(customers[i]);
            }
        }
    }

    public Queue getQueue(String type) {
        /*
         * The logic here
         * get queue with the least amount of customers of type
         * if all the same we pick the first one
         */
        Queue temp = null;
        Iterator<Queue> iter = this.queues.iterator();
        while(iter.hasNext()) {
            Queue q = iter.next();
            if(q.hasType(type) && (temp == null  || q.getLength()<temp.getLength())) {
                temp = q;
            }
        }
        return temp;
    }

    public boolean hasCustomers() {
        /*
         * dispatcher should send end signal
         * when there is no more customers to spawn
         */
        String type;
        CustomerType customerType;
        Enumeration keys = this.getCustomerTypes().keys();
        while(keys.hasMoreElements()) {
            type = keys.nextElement().toString();
            customerType = this.getCustomerTypes().get(type);
            if(!customerType.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /*
     * Custom dispatcher should inherit this class and implement the dispatch method
     */

    abstract public Customer[] dispatch(Long time);
}
