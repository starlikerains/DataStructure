package stackPackage;

/**
 * ʹ�õݹ�˼������ŵ�����⡣
 * ��ŵ�����⣺�ٶ���3�����Ӽ������ֱͬ�������ӡ�ÿ�����ӵ��м���һ���ף��������ܲ嵽�����ϡ�
 *			  �ٶ������Ѿ����������С�Ĵ���ŵ���һ�������ϣ���С�������������档�����ǽ�
 *			  ���Ӵӵ�һ�������ƶ��������������ϡ�������ԭ���Ĵ��򡣵�������ѭ���¹���
 *			 1��һ���ƶ�һ�����ӡ�ÿ��ֻ���ƶ�����������ӡ�
 *			 2�����Ӳ��ܷ��ڱ��Լ�С���������档
 *			 3��������ǰ���������ͬʱ�������õڶ��������ݴ����ӡ�
 * @ClassName: Hanoi
 * @Description: �����ģ������ŵ��������ࡣ
 * @author С��ͬѧ
 * @date 2020��11��19��
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
		System.out.println("��ŵ��ģ�⿪ʼ:\n"+pole1.poleName+"����"+Scale+"��Բ��");
		upSolveTowers(Scale,pole1,pole2,pole3);
	}
	
	
	/*
	 * ��ŵ���������ת��Ϊ�����������⣬�����㺺ŵ������������£��������Ҫ��һ��Բ�̴���ʼ���ƶ���
	 * Ŀ���̣����ǿ����Ƚ����Բ���ϵ����������ƶ����ݴ��̣�Ȼ���ٽ����Բ���ƶ���Ŀ���̣�����ݴ�
	 * ���ϵ�Բ���ƶ���Ŀ���̡����磬�������Ҫ��Բ��5������1�ƶ�������3�������Ƚ�Բ��1..4�ƶ�����2��
	 * �ٽ�Բ��5�ƶ�����3�������2�ϵ�Բ��1..4�ƶ�����3�ϣ������������һ���ƶ��������������Բ���
	 * �ݹ���ȥ��ֱ��������ֻʣ��һ��Բ�̣���ʱ������ֻ��Ҫ�����Բ���ƶ���Ŀ���̼��ɡ�
	 */
	/**
	 * 
	 * @Title: solveTowers
	 * @Description: ʹ�õݹ�˼��ģ������ŵ�����⣬��N��Բ�̴���ʼ���ƶ���Ŀ����
	 * @param numberOfDisks ��Ҫ�ƶ���Բ��������ͬʱҲ����Ҫ����ʼ���ƶ���Ŀ������Բ�̴���
	 * @param startPole Բ�̸տ�ʼʱ����������
	 * @param tempPole �����ݴ�Բ�̵�����
	 * @param endPole Ŀ������
	 * @throws 
	 */
	public void solveTowers(int numberOfDisks,Pole startPole,Pole tempPole,Pole endPole) {
		if(numberOfDisks==1) {//ֻʣ��һ��Բ����Ҫ�ƶ�������ֱ�Ӵ�startPole�ƶ���endPole
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);
			System.out.println("�� Բ��"+tempDisk.value+",��"+startPole.poleName+"�ƶ���"+endPole.poleName);
		}else {
			solveTowers(numberOfDisks-1,startPole,endPole,tempPole);//����Ҫ�ƶ���Բ�����������Բ���ƶ����ݴ���
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);//����Ҫ�ƶ���Բ�̴���ʼ���ƶ���Ŀ����
			System.out.println("�� Բ��"+tempDisk.value+",��"+startPole.poleName+"�ƶ���"+endPole.poleName);
			solveTowers(numberOfDisks-1,tempPole,startPole,endPole);//���ݴ����е��ݴ��Բ���ƶ���Ŀ����
		}
	}
	
	
	/*
	 * ���ݹ鷽��ִ�е����һ�������ǵݹ����ʱ����β�ݹ顣β�ݹ�ֻ���øı�Ĳ����ͱ����ظ��˷������߼���
	 * ͨ����β�ݹ鷽�����Ժ����׵�ת��Ϊ������������β�ݹ鷽��ת��Ϊ�����������Խ�ʡ�ݹ�����Ŀ�����
	 */
	/**
	 * 
	 * @Title: upSolveTowers
	 * @Description: ��β�ݹ��SolveTower�Ľ�Ϊʹ�õ���������upSolveTowers�����ٵݹ�Ŀ���
	 * @param numberOfDisks
	 * @param startPole
	 * @param tempPole
	 * @param endPole 
	 * @throws 
	 */
	public void upSolveTowers(int numberOfDisks,Pole startPole,Pole tempPole,Pole endPole) {
		while(numberOfDisks>0) {//�����Ľ�����������û��Բ����Ҫ�ƶ�ʱ����������
			upSolveTowers(numberOfDisks-1,startPole,endPole,tempPole);//����Ҫ�ƶ���Բ����������е�Բ���ƶ����ݴ���
			Disk tempDisk=startPole.poleStack.pop();
			endPole.poleStack.push(tempDisk);//����Ҫ�ƶ���Բ�̴���ʼ���ƶ���Ŀ����
			System.out.println("�� Բ��"+tempDisk.value+",��"+startPole.poleName+"�ƶ���"+endPole.poleName);
			numberOfDisks--;//��δ�ƶ���ɵ�Բ��������һ(����ΪnumberOfDisks��Բ���Ѿ��ɹ��ƶ�����Ŀ����)
			Pole temp=startPole;
			startPole=tempPole;
			tempPole=temp;//����Ҫ���ݴ����ϵ�Բ���ƶ���Ŀ��������ʱ���ݴ洦��Ϊ����ʼ������ʼ����Ϊ���ݴ���
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
