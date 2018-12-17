/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

package ga_tsp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TSP_GA {

    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
    	 String fileName = "D:\\Kuliah\\Semester 9\\OKH\\datasets\\medium1.csv";
    	    File file = new File(fileName); // TODO: read about File Names
    	    try {
    	        Scanner inputStream = new Scanner(file);
    	        //buat array
    	        int input=inputStream.nextInt();
    	        City a[]=new City[input];
    	        //get size array
    	       int  index=0;
    	        while (inputStream.hasNext()){
    	            String b=inputStream.next();
    	            String array1[]= b.split(",");
    	            
    	            a[index]=new City(Integer.parseInt(array1[0]),Integer.parseInt(array1[1]),(index+1));
    	            TourManager.addCity(a[index]);
    	            index++;   	       
    	        }
    	        inputStream.close();

    	    } catch (FileNotFoundException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	    }

	    PrintWriter pw = null;
        try {
        	pw = new PrintWriter( new File("Solution.csv"));
        }
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String ColumnNameList = "Distance,Solution";
        builder.append(ColumnNameList + "\n");

        // Initialize population
    	for(int a = 1; a < 6; a++) {
	        Population pop = new Population(50, true);
	        //System.out.println("Initial distance ke "+a+" : " + pop.getFittest().getDistance());
	
	        // Evolve population for 20 generations
	        pop = GA.evolvePopulation(pop);
	        for (int i = 0; i < 21; i++) {
	            pop = GA.evolvePopulation(pop);
	        }
	        
	        builder.append(pop.getFittest().getDistance());
	        builder.append(pop.getFittest() + "\n"); 
	        
    	}

        pw.write(builder.toString());
        pw.close();      
    	long endTime = System.currentTimeMillis();
    	long timeElapsed = endTime - startTime ;
    	System.out.println("Execution time in milliseconds : "+timeElapsed);
    }
}