package bagPackage;

public class LinkedBagDemo1 {
	public static void main(String[] args) {
		BagInterface<String> aBag=new LinkedBag<>();
		testEmpty(aBag,true);
		String[] content= {"I","Love","You"};
		testAdd(aBag,content);
		testEmpty(aBag,false);
		testRemove(aBag);
		testGetFrequencyOf(aBag,"I");
		testClear(aBag);
		testEmpty(aBag,true);
	}
	
	private static void testEmpty(BagInterface<String> bag,boolean empty) {
		String result=empty==bag.isEmpty()?"OK":"Error";
		System.out.println("期望值:"+empty+",实际值:"+bag.isEmpty()+"，是否正确:"+result);
	}
	
	private static void displayBag(BagInterface<String> aBag) {
		System.out.println("The bag contains the following String(s):");
		Object[] bagArray = aBag.toArray();
		for(int index=0;index<bagArray.length;index++) {
			System.out.print(bagArray[index]+" ");
		}
		System.out.println();
	}
	
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
	
	public static void testRemove(BagInterface<String> aBag) {
		System.out.println("删除:"+aBag.remove());
		displayBag(aBag);
	}
	
	public static void testGetFrequencyOf(BagInterface<String> aBag,String anEntry) {
		System.out.println("包内含有:"+anEntry+" "+aBag.getFrequencyOf(anEntry)+"个");
		displayBag(aBag);
	}
	public static void testClear(BagInterface<String> aBag) {
		aBag.clear();
		displayBag(aBag);
	}
}
