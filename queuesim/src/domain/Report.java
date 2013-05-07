package domain;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * An object that represent the resulting report
 */
public class Report {

    public long longestTime = -1;
    public long shortestTime = 1000000;
    public long averageTime = 0;
    public int totalCustomers = 0;

    public int longestQueue = 0;
    public int numberOfServiceStations = 0;

    public long simulationStartTime = 0;
    public long simulationEndTime = 0;
    public long totalTicks = 0;

    DateFormat formatter;

    public Report() {
        formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
    }

    public void addCustomerTime(long time) {
        //increment total number of customers
        this.totalCustomers++;
        //check for longest and shortest
        if(time>this.longestTime) {
            this.longestTime = time;
        }
        if(time<this.shortestTime) {
            this.shortestTime = time;
        }
        //calculate average on the fly
        this.averageTime = ((this.averageTime*this.totalCustomers)+time)/(this.totalCustomers+1);
    }

    public void writeResult(String outputFile) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "utf-8"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.simulationStartTime);
            writer.write("Simulation starts: " + formatter.format(calendar.getTime()) + "\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            writer.write("The LONGEST time spent for a customer to get serviced: "+ this.longestTime + "\r\n");
            writer.write("The SHORTEST time spent for a customer to get serviced: "+ this.shortestTime + "\r\n");
            writer.write("The AVERAGE time spent for a customer to get serviced: "+ this.averageTime + "\r\n");
            writer.write("The LONGEST queue during simulation: "+ this.longestQueue+ "\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            writer.write("Total customers serviced by " + this.numberOfServiceStations+ " service station(s): " + this.totalCustomers + "\r\n");
            writer.write("Total ticks: " + this.totalTicks+"\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            calendar.setTimeInMillis(this.simulationEndTime);
            writer.write("Simulation ends: " + this.formatter.format(calendar.getTime()) + "\r\n");

        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }
}
