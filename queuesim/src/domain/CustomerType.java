package domain;

/*
 * Class CustomerType                *
 * Represent a type that can be assigned to Customer, Queue, and ServiceStation
 *
 * name: short string that uniquely identifies this type
 * description: display name for this type
 * distribution: share of this type in a distribution function
 * e.g. if at any time 10 customers dispatched, and typeA distribution is .5
 * it means 5 of those customers are of typeA
 * serviceTime: time used to service customer of this type in the appropriate service station
 */
public class CustomerType {
    private String name;
    private String description;
    private float distribution;
    private int serviceTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDistribution() {
        return distribution;
    }

    public void setDistribution(float distribution) {
        this.distribution = distribution;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
