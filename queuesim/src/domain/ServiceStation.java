package domain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 *
 * + Customer.Type[] customerTypes : may accept multiple customer types
 * + Customer customer : current customer being serviced
 * + ArrayList queues : list of queues from which this SS can pick next customer from
 * - void wakeUp(float time)
 * - Customer getCustomer() : picks up next Customer with the correct customerTypes from queues, earlier arrivalTime first if multiple available from multiple queues.
 * - void removeCustomer()
 *
 */

public class ServiceStation {
    private String id;
	private ArrayList<String> customerTypes;
	private Customer customer;
	private ArrayList<Queue> queues;
    private long startTime = 0;
    private int totalServiced = 0;

	public ServiceStation()
	{
		this(UUID.randomUUID().toString(), new ArrayList<String>(), new ArrayList<Queue>());
	}

    public ServiceStation(String id, ArrayList<String> customerTypes, ArrayList<Queue> queues) {
        this.setId(id);
        this.setTypes(customerTypes);
        this.setQueues(queues);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public ArrayList<String> getTypes() {
		return customerTypes;
	}

	public void setTypes(ArrayList<String> types) {
		this.customerTypes = types;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Queue> getQueues() {
		return queues;
	}

	public void setQueues(ArrayList<Queue> queues) {
		this.queues = queues;
	}

    public int getTotalServiced() {
        return this.totalServiced;
    }
    
    public synchronized void getNextCustomer(long tick)
    {
    	long earliestArrival = -1;
    	Queue chosenQueue = null;
    	Customer tempCustomer = null;
    	
    	Iterator<Queue> queueIter = queues.iterator();
    	while(queueIter.hasNext()) //Iterate through queues
    	{
    		Queue tempQueue = queueIter.next();
    		Iterator<String> typeIter = customerTypes.iterator();
    		while(typeIter.hasNext()) //Iterate through Service Station types
    		{
    			String tempType = typeIter.next();
    			Customer c = tempQueue.topCustomer(tempType); //get the next available customer in the queue
    			if(c != null)
    			{
    				if(earliestArrival == -1 || c.getArrivalTime() < earliestArrival)//Check if c arrived earlier
    				{
    					tempCustomer = c;
    					chosenQueue = tempQueue;
    				}
    			}
    		}
    	}
    	
    	if(tempCustomer != null) //remove the customer with correct type and earliest arrive
    	{
    		customer = tempCustomer;
            customer.setStartServiceTime(tick);
    		chosenQueue.dequeue(customer);
    	}
    }

    public void removeCustomer() {
        this.totalServiced++;
        this.setCustomer(null);
    }
}
