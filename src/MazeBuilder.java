// Cyndi Ai & Xueming Xu
// #1236053 & #1135954
// CSE 373 Assignment #4
// Oct 29, 2013

//This is a class that builds the maze in methods of using the disjoint set
//The maze is under the constraints discussed in class that is there is only
//one solution, all cells are reachable, and only walls allowing cells to be
//reachable are removed.

import java.util.*;
import java.io.*;


public class MazeBuilder {
	
	private static MyDisjSets maze;  // the disjoint set of all numbers
	private static ArrayList<Edge> edgeNotProcess;  //the list of edges not processed
	private static ArrayList<Edge> edgeKept;   //the list of edges should be kept for print out
	
	
	public static void main(String[]args){ 
		if (args.length != 3) {
			System.err.println(" Incorrect number of arguments; the correct way is: ");
			System.err.println(" maze height, maze width, and output file name;");
			System.err.println(" for example: 5 5 output.txt");
	        System.exit(1);
	    }
		int height = Integer.parseInt(args[0]);
		int width = Integer.parseInt(args[1]);
		String file = args[2];
	
		if(width <= 0 || height <= 0) {
			System.err.println("Invalid dimenssion passed in! height and width should be >0");
			System.exit(1);
		}
		
	    try {
            PrintWriter fileOut = 
            		new PrintWriter(new
    				BufferedWriter(new FileWriter(file)));
	    	setup(height, width);
	    	findPath();
	    	draw(height, width, fileOut);
	    	for(int i = 1; i < 10; i++){
	    		if(maze.isSetName(i)){
	    			System.out.println("the set name is " + i);
	    			maze.printSet(i);
	    		}
	    	}
	    	fileOut.close();
	    } catch(IOException ioe) {
            System.err.println("Error opening/reading/writing input or output file.");
            System.exit(1);
        } catch(NumberFormatException nfe) {
            System.err.println(nfe.toString());
            System.err.println("Error in file format");
            System.exit(1);
        }
	}
	
	// set up all list for edges, and adds all inner elements into the list of 
	// edgeNotProcess
	private static void setup(int height, int width){
		maze = new MyDisjSets(height * width);
		edgeNotProcess = new ArrayList<Edge>();
	    edgeKept = new ArrayList<Edge>();
	    
		for(int i = 1; i < height * width ; i++){
			// adds all inner vertical edges
			if( i % width != 0){	
				edgeNotProcess.add(new Edge(i,i+1));
			}
			
			// adds all inner horizontal edges
			if (i + width <= height * width){
				edgeNotProcess.add(new Edge(i, i + width));
			}
		}
	}
	
	// builds the maze by the algorithm provided in lecture
	public static void findPath(){	
		Collections.shuffle(edgeNotProcess);
		while(maze.numSets() > 1){
			Edge e = edgeNotProcess.remove(edgeNotProcess.size() - 1);
		
			int u = maze.find(e.a);
			int v = maze.find(e.b);
		
			if(u == v){
				edgeKept.add(e);
			} else {
				maze.union(u, v);
			}
		}
		edgeKept.addAll(edgeNotProcess);
		edgeNotProcess = null;
	}
	
	// draws the maze into the file
	public static void draw(int h, int w, PrintWriter f){
		drawHorizontalLine(w,f);
		
		for( int i = 0; i < 2 * h - 1; i++ ){   
			if( i % 2 == 0 ){        //draw vertical line
				//consider the first entry on the top left is empty,
				//otherwise it will be the wall on the left of maze
				if( i == 0 ){
					f.print("  ");
				}  else{
					f.print("| ");
				}
				
				//check if there exist an edge between left and right
				//if edges exist, then add wall on the maze
				//otherwise, keep empty
				for( int j = 2; j <= w; j++ ){
					if(exists(w * i / 2 + j -1, w * i/2 + j)){
						f.print("| ");
					}else{
						f.print("  ");
					}
				}
				
				//consider the last entry on the bottom right is empty,
				//otherwise it will be the wall on the right of maze
				if ( i == 2 * h - 2 ){
					f.println(" ");
				} else {
					f.println("|");
				}
			} else {            					//draw the horizontal line
				f.print("+");
				for(int j = 1; j <= w; j++ ){
					
					//check if there exist an edge between top and bottom
					//if edges exist, then add wall on the maze
					//otherwise, keep empty
					if(exists(w * (i - 1) / 2 + j , w * (i - 1)/ 2 + j + w)){  
						f.print("-");
					}else{
						f.print(" ");
					}
					f.print("+");
					
				}
				f.println();
			}
		}
		drawHorizontalLine(w, f);
	}
				
	//helper method to draw horizontal line +-+- etc
	public static void drawHorizontalLine(int width, PrintWriter f){
		f.print("+");
		for(int i = 0; i < width; i++){
			f.print("-+");
		}
		f.println();
	}
	
	// returns true if the there is an edge with (a, b) in edgeKept
	// false otherwise
	private static boolean exists(int a, int b){
		for(int i = 0; i < edgeKept.size(); i++){
			if(a == edgeKept.get(i).a && b == edgeKept.get(i).b){
				return true;
			}
		}
		return false;
	}
}