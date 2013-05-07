package application;

import domain.*;

import java.util.ArrayList;

/**
 *
 */
public class Application {

	public static void main(String[] args) {
        Simulation sim =  new Simulation("config.txt", "output.txt");
        sim.start();
	}
}
