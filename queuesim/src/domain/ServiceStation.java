package domain;

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
	private ArrayList<String> customerTypes;  						// enum instead?
	private Customer currentCustomer;
	private ArrayList<Queue> accessibleQueues;
	
	
	public getCustomer(Customer customer){
		
		this.currentCustomer = customer;
		
	}
	
	public removeCustomer(Queue queue){
		
		
	}
	
	public addAccessibleQueue(Queue queue){
		
		this.accessibleQueues.add(queue);
		
	}
	
	public remAccessibleQueue(Queue queue){
		
		// Whats the best way to add/remove objects in Java?
		// this.accessibleQueues.remove(queue);
		
	}
	
	public setCurrentCustomer(Customer customer){
		
		this.currentCustomer = customer;
	}
	
	public getCurrentCustomer() {
		
		return this.currentCustomer;
		
	}

	public setCustomerTypes(ArrayList<> customerTypes) {
		
		this.customerTypes = customerTypes;
	}
	
	public getCustomerTypes() {
		
		return this.customerTypes;
	}
	
	
	
	
}
