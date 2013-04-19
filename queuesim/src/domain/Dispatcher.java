package domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Dispatcher implements Runnable {
    private float arrivalRate;
    private long sleepTime;
    private ArrayList<Queue> queues;

    public float getArrivalRate() {
        return arrivalRate;
    }

    public void setArrivalRate(float arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    public float getSleepTim() {
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

    public Dispatcher() {
        this.setArrivalRate(0);
        this.setQueues(new ArrayList<Queue>());
    }

    public void run() {
        try {
            Thread.sleep(this.sleepTime);
            
            Long time = System.currentTimeMillis();
            
            Customer customer = dispatch(time);
            
            if (customer != null) {
                
                enQueue(customer);
                
            } 

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    public Customer dispatch(Long time) {
        
        // Random num between 1 and 10
        Integer rand = 0 + (int)(Math.random() * ((10 - 0) + 1));
        
        // this is where we would inject arrival distribution
        if (time % rand == 0) {
            
            Customer c = new Customer();
            
            c.setType("TYPE");
            
            return c;
            
        } else {
            
            
            return null;
        }

    }
    
    public boolean enQueue(Customer customer) {
        
        // This is where we would inject Dispatching Policy
        
        
        
        return true;
    }

    public ArrayList<Customer> createCustomers() {
        return null;
    }

    public Queue getQueue(String type) {
        Queue temp = null;
        Iterator<Queue> iter = this.queues.iterator();
        while(iter.hasNext()) {
            Queue q = iter.next();
//            if(q.getType() == type && (temp == null  || q.getLength()<temp.getLength())) {
//                temp = q;
//            }
        }
        return temp;
    }

}
