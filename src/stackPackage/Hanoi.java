package stackPackage;

/**
 * 使用递归思想解决汉诺塔问题。
 * 汉诺塔问题：假定有3跟柱子及多个不同直径的盘子。每个盘子的中间有一个孔，这样它能插到柱子上。
 *			  假定盘子已经按从最大到最小的次序放到第一根柱子上，最小的盘子在最上面。问题是将
 *			  盘子从第一根柱子移动到第三根柱子上。并保持原来的次序。但必须遵循以下规则：
 *			 1）一次移动一个盘子。每次只能移动最上面的盘子。
 *			 2）盘子不能放在比自己小的盘子上面。
 *			 3）在遵守前两条规则的同时，可以用第二跟柱子暂存盘子。
 * @ClassName: Hanoi
 * @Description: 这个是模拟解决汉诺塔问题的类。
 * @author 小尚同学
 * @date 2020年11月19日
 *
 */
public class Hanoi {
	private static final int DEFAULT_SCALE=4;
	private int Scale;
	private Pole pole1,pole2,pole3;
	
	public Hanoi() {
		this(DEFAULT_SCALE);
	}
	
	public Hanoi(int newScale) {
		pole1=new Pole("pole1");
		pole2=new Pole("pole2");
		pole3=new Pole("pole3");
		int DiskValue=newScale;
		while(DiskValue>0) {
			Disk newDisk=new Disk(DiskValue);
			pole1.poleStack.push(newDisk);
			DiskValue--;
		}
		Scale=newScale;
	}
	public void show() {
		System.out.println("汉诺塔模拟开始:\n"+pole1.poleName+"上有"+Scale+"个圆盘");
		upSolveTowers(Scale,pole1,pole2,pole3);
	}
	
	
	/*
	 * 汉诺塔问题可以转化为这样的子问题，在满足汉诺塔问题的限制下，如果我们要将一个圆盘从起始盘移动到
	 * 目标盘，我们可以先将这个圆盘上的所有盘先移动到暂存盘，然后再将这个圆盘移动到目标盘，最后将暂存
	 * 盘上的圆盘移动到目标盘。例如，如果我们要将圆盘5从柱子1移动到柱子3，我们先将圆盘1..4移动到柱2，
	 * 再将圆盘5移动到柱3，最后将柱2上的圆盘1..4移动到柱3上，这样就完成了一次移动。这个子问题可以不断
	 * 递归下去，直到柱子上只剩下一个圆盘，此时，我们只需要将这个圆盘移动到目标盘即可。
	 */
	/**
	 * 
	 * @Title: solveTowers
	 * @Description: 使用递归思想模拟解决汉诺塔问题，将N个圆盘从起始柱移动到目标柱
	 * @param numberOfDisks 需要移动的圆盘数量，同时也是需要从起始柱移动到目标柱的圆盘代号
	 * @param startPole 圆盘刚开始时所处的柱子
	 * @param tempPole 用于暂存圆盘的柱子
	 * @param endPole 目标柱子
	 * @throws 
	 */
	public void solveTowers(int numberOfDisks,Pole startPole,Pole tempPole,Pole endPole) {
		if(numberOfDisks==1) {//只剩下一个圆盘需要移动，将其直接从startPole移动到endPole
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);
			System.out.println("将 圆盘"+tempDisk.value+",从"+startPole.poleName+"移动到"+endPole.poleName);
		}else {
			solveTowers(numberOfDisks-1,startPole,endPole,tempPole);//将需要移动的圆盘上面的所有圆盘移动到暂存柱
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);//将需要移动的圆盘从起始柱移动到目标柱
			System.out.println("将 圆盘"+tempDisk.value+",从"+startPole.poleName+"移动到"+endPole.poleName);
			solveTowers(numberOfDisks-1,tempPole,startPole,endPole);//将暂存柱中的暂存的圆盘移动到目标柱
		}
	}
	
	
	/*
	 * 当递归方法执行的最后一个动作是递归调用时发生尾递归。尾递归只是用改变的参数和变量重复了方法的逻辑。
	 * 通常，尾递归方法可以很容易的转化为迭代方法，将尾递归方法转化为迭代方法可以节省递归带来的开销。
	 */
	/**
	 * 
	 * @Title: upSolveTowers
	 * @Description: 将尾递归的SolveTower改进为使用迭代方法的upSolveTowers，减少递归的开销
	 * @param numberOfDisks
	 * @param startPole
	 * @param tempPole
	 * @param endPole 
	 * @throws 
	 */
	public void upSolveTowers(int numberOfDisks,Pole startPole,Pole tempPole,Pole endPole) {
		while(numberOfDisks>0) {//迭代的结束条件，当没有圆盘需要移动时，结束迭代
			upSolveTowers(numberOfDisks-1,startPole,endPole,tempPole);//将需要移动的圆盘上面的所有的圆盘移动到暂存柱
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);//将需要移动的圆盘从起始柱移动到目标柱
			System.out.println("将 圆盘"+tempDisk.value+",从"+startPole.poleName+"移动到"+endPole.poleName);
			numberOfDisks--;//还未移动完成的圆盘数量减一(代号为numberOfDisks的圆盘已经成功移动到了目标柱)
			Pole temp=startPole;
			startPole=tempPole;
			tempPole=temp;//现在要将暂存柱上的圆盘移动到目标柱，此时，暂存处成为了起始柱，起始柱变为了暂存柱
		}
	}
	
	private class Disk{
		int value;
		public Disk(int newValue) {
			value=newValue;
		}
	}
	private class Pole{
		private StackInterface<Disk> poleStack;
		private String poleName;
		public Pole(String newName) {
			poleStack=new LinkedStack<>();
			poleName=newName;
		}
	}
	
	public static void main(String[] args) {
		Hanoi aHanoi=new Hanoi(4);
		aHanoi.show();
	}
	
}
