package application;

import domain.*;

import java.util.ArrayList;

/**
 *
 */
public class Application {

	public static void main(String[] args) {
        //String config = "interpreter? / xml?";
                 
        //domain.Simulation sim = new Simulation(config);
        //sim.build();
        //sim.run();

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
        CustomerType expressType = new CustomerType("Express", "Express Customers", 10, 100);
        CustomerType regularType = new CustomerType("Regular", "Regular Customers", 50, 300);
        ArrayList<CustomerType> allTypes = new ArrayList<CustomerType>();
        allTypes.add(expressType);
        allTypes.add(regularType);
        //string version
        ArrayList<String> expressOnly = new ArrayList<String>();
        expressOnly.add(expressType.getName());
        ArrayList<String> allCustomers = new ArrayList<String>();
        allCustomers.add(expressType.getName());
        allCustomers.add(regularType.getName());

        //create queues
        ArrayList<Queue> allQueues = new ArrayList<Queue>();
        allQueues.add(new Queue("Q1", expressOnly));
        allQueues.add(new Queue("Q2", allCustomers));
        allQueues.add(new Queue("Q3", allCustomers));

        //create service stations
        ServiceStation ss1 = new ServiceStation("SS1", expressOnly, allQueues);
        ServiceStation ss2 = new ServiceStation("SS2", allCustomers, allQueues);
        ServiceStation ss3 = new ServiceStation("SS3", allCustomers, allQueues);

		//init with 50 express customers and 50 regular customers
        SimpleDispatcher dispatcher = new SimpleDispatcher(1, 10);
        dispatcher.run();
	}
	
	
	
}
