// this is a class implements the interface Disjoinsets.java to create a correct
// union find ADT.

public class MyDisjSets {
	private int[] up; //stores the data and sequence
	private int[] weight; //stores the weight
	private int size; //stores numElements
	
	// constructor, initializes all variables
	public MyDisjSets(int numElements){
		size = numElements;
		up = new int[size + 1];
		weight = new int[size + 1];
		
		//initializes the array weight
		for(int i = 1; i <= size; i++){
			weight[i] = 1; 
		}
	}
	
	// unions the set1 and sets2 according to their weight
	// lower weighted gets union onto the heavier weighted
	// when two are equal weighted, the second one unions onto first
	// if either set1 or set2 is not an element or is not a root, throws exception
    public void union(int set1, int set2){
    	int numEle1 = numElements(set1); //gets the weight
    	int numEle2 = numElements(set2); 
    	
    	if(numEle1 >= numEle2){
    		up[set2] = set1; 
    		weight[set1] += weight[set2];
    	} else {
    		up[set1] = set2;
    		weight[set2] += weight[set1];
    	}
    }
    
    // finds the root of the element x gets passed in
    // compresses the path in convenience for later finding operations
    // throws exceptions when x is not an element in the array up
    public int find(int x){
    	testElement(x);
    	int temp = x;
    	
    	//find the root
    	while(up[temp] != 0)
    		temp = up[temp];
    	
    	//case where x is the root
    	if(x==temp)
    		return temp;
    	
    	//compress the path
    	int old_parent = up[x];
    	while(old_parent!= temp){
    		up[x] = temp;  //directly point at root
    		x = old_parent;
    		old_parent = up[x];
    	}
    	return temp;
    }
   
    // returns the total number of sets in the array up
    public int numSets(){
    	int numSets = 0;
    	for(int i = 1; i <= size; i++){
    		if(up[i] == 0)
    			numSets++;
    	}
    	return numSets;
    }

    // returns a boolean indicating whether the x element is a set name
    // throws exception when x is not a valid element in array
    public boolean isSetName(int x){
    	testElement(x);
    	return up[x] == 0; 
    }
    
    // returns the total number of elements inside the set, or in other word
    // the weight of the set called setNum
    // throws exception when setNum is not a valid element inside the array or
    // setNum is not a name for a set
    public int numElements(int setNum){
    	testBoth(setNum);
    	return weight[setNum];
    }

    // prints the set with the name of setNum, in format of {a, b,....}
    // throws exception when setNum is not a valid element or not a name for a set
    public void printSet(int setNum){
    	testBoth(setNum);
    	int[] elements = getElements(setNum); //gets the array
    	System.out.print("{" + elements[0]);
    	for(int i = 1; i < elements.length; i++){
    		System.out.print(", " + elements[i]);
    	}
    	System.out.print("}");
    	
    }

    // gets all element with the name of setNum in an array in any order and return it
    // throws exception when setNum is not a valid element or not a name for a set
    public int [] getElements(int setNum){
    	testBoth(setNum);
    	int[] elements = new int[numElements(setNum)];
    	int index = 0;
    	for(int i = 0; i<=size; i++){
    		if(find(i) == setNum){
    			elements[index] = i;
    			index++;
    		}
    			
    	}
    	return elements;
    }
    
    // tests if the element x is in the array, if not throw exception
    private void testElement(int x){
    	if(x > size + 1 || x < 0)
    		throw new InvalidElementException("X is not a valid element");
    }

    // tests if the element x is name of a set, if not throw exception
    private void testSet(int x){
    	if(!isSetName(x))
    		throw new InvalidSetNameException("setNum is not the name of a set");
    }
    
    // tests both if it is a name of a set and is an element. throw exception elsewise
    private void testBoth(int x){
    	testElement(x);
    	testSet(x);
    }
}
