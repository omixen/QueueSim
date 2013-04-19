/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.ArrayList;
/**
 *
 * @author Cody
 */
public class Simulation {
    
    
    private String config;
    private Dispatcher dispatcher;
    private ArrayList<ServiceStation> stations;
    
    public Simulation(String config) {
        
        this.config = config;
        this.dispatcher = new Dispatcher();
    }

    public void build() {
         // read config
        Integer stationCount = 5;
        Integer queueCount = 5;
        
        
        // Create the stations
        for(Integer i = 0; i < stationCount; i++){
           
          ServiceStation s = new ServiceStation();
          
          this.stations.add(s);
  
       }

       // Create the queues
       for(Integer i = 0; i < queueCount; i++){
           
          Queue q = new Queue();
          
          this.dispatcher.addQueue(q);
  
       }

    }
    
    // Maybe this could be runnable but i'm not familiar with it
    public void start(){
        
        this.dispatcher.run();
        
    } 
    
    
    
}
