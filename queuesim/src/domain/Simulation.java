/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.Hashtable;
/**
 *
 * @author Cody
 */
public class Simulation implements Runnable {

    private Config config;
    private String configFile = "config.txt";
    private String outputFile = "output.txt";
    private String outputLog = "output.log";

    public Hashtable<String, CustomerType> allTypes;
    public ArrayList<Queue> allQueues;
    public ArrayList<ServiceStation> allStations;
    public Thread mainThread;
    public Dispatcher dispatcher;
    public Report report;
    private Boolean running = true;
    private int sleepTime = 200;
    private long tick = 0;
    private long maxTicks = 1800;

    public Simulation(String config, String output) {

        this.configFile = config;
        this.outputFile = output;

        /*
         * Setiawan: single service station, multiple queues, customers dispatched to shortest queue, fifo queuing policy.
         * since single customer type, auto fifo
         */

        //create customer types and groups
        allTypes = new Hashtable<String, CustomerType>();
        CustomerType regularType = new CustomerType("Regular", "Regular Customers", 5, 100);
        //CustomerType quickType = new CustomerType("Quick", "Quick Customers", 3, 50);
        //allTypes.put("Quick", quickType);
        allTypes.put("Regular", regularType);

        //string version
        ArrayList<String> allCustomers = new ArrayList<String>();
        //allCustomers.add(quickType.getName());
        allCustomers.add(regularType.getName());

        //create queues
        allQueues = new ArrayList<Queue>();
        allQueues.add(new Queue("Q1", allCustomers));
        allQueues.add(new Queue("Q2", allCustomers));
        allQueues.add(new Queue("Q3", allCustomers));

        //create service stations
        allStations = new ArrayList<ServiceStation>();
        ServiceStation ss1 = new ServiceStation("SS1", allCustomers, allQueues);
        //ServiceStation ss2 = new ServiceStation("SS2", allCustomers, allQueues);
        allStations.add(ss1);
        //allStations.add(ss2);

        //init with 50 express customers and 50 regular customers
        dispatcher = new SimpleDispatcher(1, 1);
        dispatcher.setCustomerTypes(allTypes);
        dispatcher.setQueues(allQueues);

        //init report
        report = new Report();
        report.numberOfServiceStations = allStations.size();
    }

    public void start(){
        try {
            //create and start thread on this class
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
            //points system.out to a file
            System.setOut(new PrintStream(new FileOutputStream(this.outputLog)));
            report.simulationStartTime = System.currentTimeMillis();

            //main thread body
            while(this.running) {
                //dispatch customers  for this tick
                dispatcher.dispatchCustomers(this.tick);

                //register longest queue, if we get pub sub it would be better here
                for(Queue q : this.allQueues) {
                    if(q.getLength()>report.longestQueue) {
                        report.longestQueue = q.getLength();
                    }
                }

                //update customers in service stations
                for(ServiceStation ss : this.allStations) {
                    //is this service station servicing a customer
                    if(ss.getCustomer() != null) {
                        //is it done
                        Customer customer = ss.getCustomer();
                        if(this.tick >= (customer.getStartServiceTime()+customer.getServiceTime())) {
                            //register customer time for the report
                            report.addCustomerTime(this.tick-customer.getArrivalTime());
                            ss.removeCustomer();
                        }
                    }
                    //if empty by now, get next customer
                    if(ss.getCustomer() == null) {
                        ss.getNextCustomer(this.tick);
                    }
                }

                //if no more customer to dispatch
                // and no more customer in queue and service station
                //or we go over the maxTicks (timeout), exit
                if((!dispatcher.hasCustomers() && !this.hasCustomersInService()) || this.tick > this.maxTicks) {
                    exit();
                }else {
                    this.tick++;
                }
                Thread.sleep(this.sleepTime);
            }

            report.totalTicks = this.tick;
            report.simulationEndTime = System.currentTimeMillis();

            //write report result
            report.writeResult(this.outputFile);

        } catch(FileNotFoundException|InterruptedException ie) {
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
}
