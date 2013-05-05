/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.ArrayList;
import java.util.Set;
import java.util.Hashtable;
/**
 *
 * @author Cody
 */
public class Simulation implements Runnable {

    private String configpath;
    private Config config;

    public ArrayList<CustomerType> allTypes;
    public ArrayList<Queue> allQueues;
    public ArrayList<ServiceStation> allStations;
    public ArrayList<Thread> allThreads;
    public Thread mainThread;
    public Dispatcher dispatcher;
    private Boolean running = true;
    private int sleepTime = 1000;

    public Simulation(String config) {

        /*SHOPPING CHECKOUT SCENARIO
         * Customer Types:
         *   Express customers (X items or less)
         *   Regular customers (> X items)
         * Number of queues = Number of service stations
         * Regular customers can only be assigned to non-express queues
         * Express customers can be assigned to either express or non-express queues
         * (This may be modified with smarter logic in the dispatcher calculating using the type serviceTime)
        */

        //create customer types and groups
        CustomerType expressType = new CustomerType("Express", "Express Customers", 10, 100);
        CustomerType regularType = new CustomerType("Regular", "Regular Customers", 50, 300);
        allTypes = new ArrayList<CustomerType>();
        allTypes.add(expressType);
        allTypes.add(regularType);

        //string version
        ArrayList<String> expressOnly = new ArrayList<String>();
        expressOnly.add(expressType.getName());
        ArrayList<String> allCustomers = new ArrayList<String>();
        allCustomers.add(expressType.getName());
        allCustomers.add(regularType.getName());

        //create queues
        allQueues = new ArrayList<Queue>();
        allQueues.add(new Queue("Q1", expressOnly));
        allQueues.add(new Queue("Q2", allCustomers));
        allQueues.add(new Queue("Q3", allCustomers));

        //create service stations
        ServiceStation ss1 = new ServiceStation("SS1", expressOnly, allQueues);
        ServiceStation ss2 = new ServiceStation("SS2", allCustomers, allQueues);
        ServiceStation ss3 = new ServiceStation("SS3", allCustomers, allQueues);
        allStations.add(ss1);
        allStations.add(ss2);
        allStations.add(ss3);

        //init with 50 express customers and 50 regular customers
        dispatcher = new SimpleDispatcher(1, 10);

        try {
            //start dispatcher thread
            allThreads.add(dispatcher.start());
            //start service station threads
            for(ServiceStation ss : allStations) {
                allThreads.add(ss.start());
            }
            //start main thread
            mainThread = this.start();

        } catch (Exception e){
            
             System.out.println(e.getMessage());
        }
    }

    public Thread start(){
        //create, start and return thread
        Thread thread = (new Thread(this));
        thread.start();
        return thread;
    }

    public void exit(){
        this.running = false;
    }

    public void run() {
        try {
            boolean done = true;
            while(this.running) {
                //check if dispatcher is done dispatching
                //and if all customers have been serviced
                if(this.hasCustomersInService()) {
                    this.exit();
                }
                Thread.sleep(this.sleepTime);
            }
            //make sure we exit all other threads
            //when this one is closed


        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public boolean hasCustomersInService() {
        for(Queue q : this.allQueues) {
            if(q.getLength()>0) {
                return false;
            }
        }
        for(ServiceStation ss : this.allStations) {
            if(ss.getCustomer() != null) {
                return false;
            }
        }
        return true;
    }
}
