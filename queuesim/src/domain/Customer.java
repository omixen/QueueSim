package domain;

/*
 * Class Customer
 * Represent a customer that being dispatched by the dispatcher,
 * inserted into queues, and serviced by the service stations
 * id: short string that uniquely identifies this customer
 * type: customer type, assigned by dispatcher during dispatching
 * arrivalTime: set by dispatcher during dispatching, used in calculating total time by observer
 */
public class Customer {
    private String id;
    private CustomerType type;
    private int arrivalTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
