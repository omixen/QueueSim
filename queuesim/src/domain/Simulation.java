/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.ArrayList;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Hashtable;
/**
 *
 * @author Cody
 */
public class Simulation implements Runnable {

    private String configpath;
    private Config config;
    private String outputFile = "output.txt";

    public Hashtable<String, CustomerType> allTypes;
    public ArrayList<Queue> allQueues;
    public ArrayList<ServiceStation> allStations;
    public Thread mainThread;
    public Dispatcher dispatcher;
    private Boolean running = true;
    private int sleepTime = 1000;
    private long tick = 0;
    private long maxTicks = 10;

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
        allTypes = new Hashtable<String, CustomerType>();
        CustomerType regularType = new CustomerType("Regular", "Regular Customers", 50, 300);
        allTypes.put("Regular", regularType);

        //string version
        ArrayList<String> allCustomers = new ArrayList<String>();
        allCustomers.add(regularType.getName());

        //create queues
        allQueues = new ArrayList<Queue>();
        allQueues.add(new Queue("Q1", allCustomers));
        allQueues.add(new Queue("Q2", allCustomers));
        allQueues.add(new Queue("Q3", allCustomers));

        //create service stations
        allStations = new ArrayList<ServiceStation>();
        ServiceStation ss1 = new ServiceStation("SS1", allCustomers, allQueues);
        ServiceStation ss2 = new ServiceStation("SS2", allCustomers, allQueues);
        ServiceStation ss3 = new ServiceStation("SS3", allCustomers, allQueues);
        allStations.add(ss1);
        allStations.add(ss2);
        allStations.add(ss3);

        //init with 50 express customers and 50 regular customers
        dispatcher = new SimpleDispatcher(1, 1);
        dispatcher.setCustomerTypes(allTypes);
        dispatcher.setQueues(allQueues);
    }

    public void start(){
        try {
            //create, start and return thread
            mainThread = (new Thread(this));
            mainThread.start();
            System.out.println("Simulation has been started successfully!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void exit(){
        this.running = false;
    }

    public void run() {
        try {
            while(this.running) {
                this.tick++;
                //dispatch customers  for this tick
                dispatcher.dispatchCustomers(this.tick);

                //update customers in service stations
                for(ServiceStation ss : this.allStations) {
                    if(ss.getCustomer() != null) {

                    }
                }

                //if no more customer in queue and service station
                //or we go over the maxTicks (timeout), exit
                if(!this.hasCustomersInService() || this.tick > this.maxTicks) {
                    exit();
                }
                Thread.sleep(this.sleepTime);
            }
            //write report result
            writeResult(this.tick);

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public boolean hasCustomersInService() {
        for(Queue q : this.allQueues) {
            if(q.getLength()>0) {
                return true;
            }
        }
        for(ServiceStation ss : this.allStations) {
            if(ss.getCustomer() != null) {
                return true;
            }
        }
        return false;
    }

    public void writeResult(long tick) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(this.outputFile), "utf-8"));
            writer.write("Something is happening\n");
            writer.write("tick is: "+tick);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }
}
