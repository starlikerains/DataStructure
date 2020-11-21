package bagPackage;

public class BagDemo {
	//Tests the method add.
	public static void testAdd(BagInterface<String> aBag,
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
	public static void displayBag(BagInterface<String> aBag) {
		System.out.println("The bag contains the following String(s):");
		Object[] bagArray = aBag.toArray();
		for(int index=0;index<bagArray.length;index++) {
			System.out.print(bagArray[index]+" ");
		}
		System.out.println();
	}
	
	//Tests the mothod isEmpty.
	public static void testEmpty(BagInterface<String> bag,boolean empty) {
		String result=empty==bag.isEmpty()?"OK":"Error";
		System.out.println("期望值:"+empty+",实际值:"+bag.isEmpty()+"，是否正确:"+result);
	}
}
