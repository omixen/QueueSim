package domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Queue {

    private ArrayList<Customer> customers;
    private ArrayList<CustomerType> customerTypes;

    public Queue() {
        customers = new ArrayList<Customer>();
        customerTypes = new ArrayList<CustomerType>();
    }

    public ArrayList<CustomerType> getType() {
        return customerTypes;
    }

    public void setType(ArrayList<CustomerType> type) {
        this.customerTypes = type;
    }

    public void addType(CustomerType t) {
        if (t != null) {
            customerTypes.add(t);
        }
    }

    public boolean hasType(CustomerType t) {
        if(customerTypes.contains(t)) {
            return true;
        }
        return false;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> c) {
        this.customers = c;
    }

    public int getLength() {
        return customers.size();
    }

    public void enqueue(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

   public Customer dequeue(String type) {
        if (type != null) {
            Iterator<Customer> custIter = customers.iterator();
            while (custIter.hasNext()) {
                Customer c = custIter.next();
                String t = c.getType().getName();
                if (type.equals(t)) 
                {
                        return c;   
                }
            }
        }
        return null;
    }
}
