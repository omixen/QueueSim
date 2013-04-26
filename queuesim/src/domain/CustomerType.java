package domain;

/*
 * Class CustomerType                *
 * Represent a type that can be assigned to Customer, Queue, and ServiceStation
 *
 * name: short string that uniquely identifies this type
 * description: display name for this type
 * serviceTime: time used to service customer of this type in the appropriate service station
 */
public class CustomerType {
    private String name;
    private String description;
    private int serviceTime;

    public CustomerType(String name, String description, int serviceTime) {
        this.setName(name);
        this.setDescription(description);
        this.setServiceTime(serviceTime);
    }

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

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
