/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Cody
 */
public class Simulation {
    
    
    private String config;
    private Dispatcher dispatcher;
    
    public Simulation(String config) {
        
        this.config = config;
        this.dispatcher = new Dispatcher();
    }

    public void build() {
        
        // read config
        Integer qcount = 5;

       for(Integer i = 0; i < qcount; i++){
           
          Queue q = new Queue();
          
          this.dispatcher.addQueue(q);
  
       }
       
       
  
    }
    
    public void run(){
        
        this.dispatcher.run();
        
    }
    
    
    
}
