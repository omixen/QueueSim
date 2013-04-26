package domain;

import java.util.UUID;
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
    private String type;
    private long arrivalTime;

    public Customer(String type, long arrivalTime) {
        this.setId(UUID.randomUUID().toString());
        this.setType(type);
        this.setArrivalTime(arrivalTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(CustomerType type) {
        this.type = type.getName();
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
