package main;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import readJson.*;
import parametersLoader.*;
import core.core;
public class main {
	
    public final static Scanner sc = new Scanner(System.in);
    public final static Random rn = new Random();
    
    public static loadParameters loadParameters = new loadParameters();
    
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException, JSONException {	
    	
    	while(true) {

	    	System.out.println("Select game (1,2,3): ");
	        System.out.println("1: Las tres fuerzas de El Dorado.");
	        System.out.println("2: La Fortuna estelar.");
	        System.out.println("3: Las riquezas de la Alquimia");
	        
	        String gameSelection = sc.nextLine();
     	
        	for(Parameters param : loadParameters.getParameters()) {
        		if(param.getGameId()==Integer.parseInt(gameSelection)) {
        			new core(param);
        		}
        	}   	
        }
    } 
}
