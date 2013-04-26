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
    private ArrayList<String> customerTypes;

    public Queue() {
        this(UUID.randomUUID().toString(), new ArrayList<String>());
    }

    public Queue(String id, ArrayList<String> types) {
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

    public ArrayList<String> getType() {
        return customerTypes;
    }

    public void setTypes(ArrayList<String> types) {
        this.customerTypes = types;
    }

    public void addType(String t) {
        if (t != null) {
            customerTypes.add(t);
        }
    }

    public boolean hasType(String t) {
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
                String t = c.getType();
                if (type == t)
                {
                    custIter.remove();
                    return c;   
                }
            }
        }
        return null;
    }
}
