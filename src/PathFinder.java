import java.io.*;
import java.util.*;


public class PathFinder {
	
	private static int[] pred; 
	private static List<String> lines;
	
	public static void main(String[] args) throws IOException {
		setup(args[0]);
		
		pred = new int[5];
	}
	
	private static void setup(String file){
		File f = new File(file);
		try {
			Scanner s = new Scanner(f);
			lines = new ArrayList<String>();
			while(s.hasNext())
				lines.add(s.next());
			
			int width = (lines.get(1).split("+")).length;
			int height = lines.size();
			pred = new int[height * width];
			for(int v = 0; v < height * width; v++){
				pred[v] = -1;
			}
		} catch (FileNotFoundException e) {
			System.err.print("wrong wrong wrong");
		}
	}
}
