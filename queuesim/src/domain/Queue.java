package domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Queue {

    private ArrayList<Customer> customers;
    private ArrayList<String> type;

    public Queue() {
        customers = new ArrayList<Customer>();
        type = new ArrayList<String>();
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public void addType(String t) {
        if (t != null) {
            type.add(t);
        }
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
//        if (type != null) {
//            Iterator<Customer> custIter = customers.iterator();
//            while (custIter.hasNext()) {
//                Customer c = custIter.next();
//                Iterator<String> typeIter = c.getType().iterator();
//                while (typeIter.hasNext()) {
//                    String t = typeIter.next();
//                    if (type.equals(t)) {
//                        return c;
//                    }
//                }
//            }
//        }
        return null;
    }
}