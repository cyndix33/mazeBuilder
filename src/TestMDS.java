
// a class tests the accuracy of all methods in MyDisjSets
public class TestMDS {
	private static MyDisjSets myDS;
	
	public static void main(String[] args){
		myDS = new MyDisjSets(9);
		
		testUnion();
		testFind();
		test();
		//testExceptions();
	}
	
   //unions stuff
	public static void testUnion(){
		myDS.union(1, 8);
		myDS.union(5, 6);
		myDS.union(7, 5);
		myDS.union(1, 5);
		myDS.union(2, 3);
		myDS.union(4, 2);		
  	}
	
	//tests find
	public static void testFind(){
		System.out.println("This is the test of find: ");
		int find8 = myDS.find(8);
		int find5 = myDS.find(5);
		int find4 = myDS.find(4);
		System.out.println("find4 is "+ find4);
		System.out.println("find5 is " + find5);
		System.out.println("find8 is " + find8);
	}

   //general test, tests several things
	public static void test(){
      //tests numSets
		int numSets = myDS.numSets();
		System.out.println("the total number set is "+ numSets + "\n");
		
		//tests isSetName
		boolean setName5 = myDS.isSetName(5);
		boolean setName2 = myDS.isSetName(2);
		if(setName5)
			System.out.println("5 is a set name");
		if(setName2)
			System.out.println("2 is a set name");
	   	System.out.println();
		
		//tests numEle
		int five = myDS.numElements(5);
		int nine = myDS.numElements(9);
		System.out.println("5 has "+ five + "elements and 9 has "+ nine + " elements" + "\n");
	   	
		//tests printSets and getElements
	   	myDS.printSet(5);
	   	myDS.printSet(9);
	   	myDS.printSet(2);
	}
}
