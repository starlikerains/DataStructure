package bagPackage;

public class ArrayBagDemo1 {
	public static void main(String[] args) {
		//Adding to an initially empty bag with sufficient capacity
		System.out.println("Testing an initially empty bag with"+
							" the capacity to hold at least 6 strings:");
		BagInterface<String> aBag=new ArrayBag<>();
		String[] contentsOfBag1= {"A","A","B","A","C","A"};
		testAdd(aBag,contentsOfBag1);
		
		//Filling an initially empty bag to capacity
		System.out.println("\nTesting an initially empty bag that "+
							" will be filled to capacity:");
		aBag=new ArrayBag<>(7);
		String[] contentsOfBag2= {"A","B","A","C","B","C","D",
									"another string"};
		testAdd(aBag,contentsOfBag2);
	}
	
	//Tests the method add.
	private static void testAdd(BagInterface<String> aBag,
								String[] content) {
		System.out.print("Adding the following "+content.length+
							" strings to the bag: ");
		for(int index=0;index<content.length;index++) {
			if(aBag.add(content[index]))
				System.out.print(content[index]+" ");
			else
				System.out.print("\nUnable to add "+content[index]+" to the bag.");
		}
		System.out.println();
		displayBag(aBag);
	}
	
	//Tests the method toArray while displaying the bag.
	private static void displayBag(BagInterface<String> aBag) {
		System.out.println("The bag contains the following String(s):");
		Object[] bagArray = aBag.toArray();
		for(int index=0;index<bagArray.length;index++) {
			System.out.print(bagArray[index]+" ");
		}
		System.out.println();
	}
}
