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
 *
 */
public class ServiceStation {
    private String id;
	private ArrayList<CustomerType> customerTypes;
	private Customer customer;
	private ArrayList<Queue> queues;
	private long sleepTime;
	
	public ServiceStation()
	{
		this(UUID.randomUUID().toString(), new ArrayList<CustomerType>(), new ArrayList<Queue>());
	}

    public ServiceStation(String id, ArrayList<CustomerType> customerTypes, ArrayList<Queue> queues) {
        this.setId(id);
        this.setTypes(customerTypes);
        this.setQueues(queues);
        this.setSleepTime(0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public ArrayList<CustomerType> getTypes() {
		return customerTypes;
	}

	public void setTypes(ArrayList<CustomerType> types) {
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

	public float getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
    
    public void getNextCustomer()
    {
    	long earliestArrival = -1;
    	Iterator<Queue> queueIter = queues.iterator();
    	while(queueIter.hasNext()) //Iterate through queues
    	{
    		Queue tempQueue = queueIter.next();
    		Iterator<CustomerType> typeIter = customerTypes.iterator();
    		while(typeIter.hasNext()) //Iterate through Service Station types
    		{
    			CustomerType tempType = typeIter.next();
    			Customer c = tempQueue.dequeue(tempType); //get the next available customer in the queue
    			if(c != null)
    			{
    				if(earliestArrival == -1 || c.getArrivalTime() < earliestArrival)//Check if c arrived earlier
    				{
    					customer = c;
    				}
    			}
    		}
    	}
    }
	
    public void service()
    {
    	//service the customer
    	customer = null; //Service station is finished with the customer
    }
    
    public void run() {
        try {
            Thread.sleep(this.sleepTime);
            getNextCustomer();
            //Long time = System.currentTimeMillis();

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
