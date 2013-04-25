package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *  Abstract Class Dispatcher
 *  Base class for all dispatchers
 */
public abstract class Dispatcher implements Runnable {
    private float arrivalRate;
    private long sleepTime;
    private ArrayList<Queue> queues;
    private Hashtable<CustomerType, Integer> customers;
    private boolean running = true;

    public float getArrivalRate() {
        return arrivalRate;
    }

    public void setArrivalRate(float arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    public float getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

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

    public ArrayList<CustomerType> getCustomerTypes() {
        return Collections.list(this.customers.keys());
    }

    public void run() {
        try {
            Thread.sleep(this.sleepTime);
            
            Long time = System.currentTimeMillis();
            
            Customer[] customers = dispatch(time);
            
            if (customers != null) {
                assignCustomers(customers);
            } 

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    public void assignCustomers(Customer[] customers) {
        for(int i=0;i<customers.length;i++) {
            getQueue(customers[i].getType()).enqueue(customers[i]);
        }
    }

    public Queue getQueue(CustomerType type) {
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

    abstract public Customer[] dispatch(Long time);
}
