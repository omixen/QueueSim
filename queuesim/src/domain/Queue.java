package domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Queue implements Runnable {
	
	private ArrayList<Customer> customers;
	private ArrayList<String> type;
	//private string type;
	private long sleepTime;
	
	public Queue() {
		customers = new ArrayList<Customer>();
		type = new ArrayList<String>();
	}
	
	public ArrayList<String> getType()
	{
		return type;
	}
	
	public void setType(ArrayList<String> type)
	{
		this.type = type;
	}
	
	public void addType(String t);
	{
		if(t != null)
		{
			type.add(t);
		}
	}
	
	public long getSleepTime() 
	{
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) 
    {
        this.sleepTime = sleepTime;
    }
	
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
	public void setCustomers(ArrayList<Customer> c)
	{
		this.customers = c;
	}
	
	public void run() {
        try {
            Thread.sleep(this.sleepTime);

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
	
	public int getLength()
	{
		return customers.size();
	}
	
	public void enqueue(Customer customer)
	{
		if(customer != null)
		{
			customers.add(customer);
		}
	}
	
	public Customer dequeue(String type)
	{
		if(type != null)
		{
			Iterator<Customers> custIter = customers.iterator();
			while(custIter.hasNext())
			{
				Customer c = custIter.next();
				Iterator<String> typeIter = c.getType().iterator();
				while(typeIter.hasNext())
				{
					String t = typeIter.next();
					if(type.equals(t))
					{
						return c;
					}
				}
			}
		}
		return null;
	}
	
}
