package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 *
 */
public class Queue {
    private String id;
    private ArrayList<Customer> customers;
    private ArrayList<CustomerType> customerTypes;

    public Queue() {
        this(UUID.randomUUID().toString(), new ArrayList<CustomerType>());
    }

    public Queue(String id, ArrayList<CustomerType> types) {
        this.setId(id);
        this.setTypes(types);
        this.setCustomers(new ArrayList<Customer>());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<CustomerType> getType() {
        return customerTypes;
    }

    public void setTypes(ArrayList<CustomerType> types) {
        this.customerTypes = types;
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

   public Customer dequeue(CustomerType type) {
        if (type != null) {
            Iterator<Customer> custIter = customers.iterator();
            while (custIter.hasNext()) {
                Customer c = custIter.next();
                String t = c.getType().getName();
                if (type.getName().equals(t)) 
                {
                    custIter.remove();
                    return c;   
                }
            }
        }
        return null;
    }
}
