package queue;


import exception.EmptyQueueException;

/**
 * WaitLine���ö��нṹ��ģ���Ŷ�������ࡣ�������˿�ƽ���ȴ�ʱ��
 * @ClassName: WaitLine
 * @Description: WaitLine���ö��нṹ��ģ��������ࡣ
 * @author С��ͬѧ
 * @date 2020��11��21��
 *
 */
public class WaitLine {
	private QueueInterface<Customer> line;
	private int numberOfArrivals;//�ѵ�������
	private int numberServed;//�ѷ�������
	private int totalTimeWaited;//���й˿��ܵȴ�ʱ��
	
	public WaitLine() {
		line=new LinkedQueue<>();
		reset();
	}
	
	public void simulate(int duration,double arrivalProbability,
						 int maxTransactionTime) throws EmptyQueueException {
		int transactionTimeLeft=0;
		for(int clock=0;clock<duration;clock++) {
			if(Math.random()<arrivalProbability) {
				numberOfArrivals++;
				int transactionTime=(int)(Math.random()*(maxTransactionTime-1)+1);
				Customer nextArrival=new Customer(clock,transactionTime,numberOfArrivals);
				line.enqueue(nextArrival);
				System.out.println("�˿� "+numberOfArrivals
								   +" �� "+clock
								   +" ʱ����. �������ʱ��Ϊ "
								   +transactionTime);	
			}
			if(transactionTimeLeft>0) {
				transactionTimeLeft--;
			}else if(!line.isEmpty()) {
				Customer nextCustomer=line.dequeue();
				transactionTimeLeft=nextCustomer.getTransactionTime()-1;
				int timeWaited=clock-nextCustomer.getArrivalTime();
				totalTimeWaited+=timeWaited;
				numberServed++;
				System.out.println("�˿�"+nextCustomer.getCustomerNumber()
								   +" �� "+clock
								   +" ʱ��ʼ���񡣵ȴ�ʱ��Ϊ"+timeWaited);
			}
		}
		line.clear();
	}
	
	public void displayResults() {
		System.out.println();
		System.out.println("�ܷ�������= "+numberServed);
		System.out.println("�ܵȴ�ʱ��= "+totalTimeWaited);
		double averageTimeWaited=((double)totalTimeWaited)/numberServed;
		System.out.println("ƽ���ȴ�ʱ��= "+averageTimeWaited);
	}
	
	public final void reset() {
		line.clear();
		numberOfArrivals=0;
		numberServed=0;
		totalTimeWaited=0;
	}
	public static void main(String[] args) throws EmptyQueueException {
		WaitLine test=new WaitLine();
		System.out.println("�ƶ�Ӫҵ���˿��Ŷ��龰ģ��");
		System.out.println("Ӫҵʱ��Ϊ: \n\t09:00-12:00\n"+"\t14:00-17:50");
		System.out.println("�����Ŷ����ģ��");
		test.simulate(180, 0.2, 10);
		System.out.println();
		System.out.println("�����Ŷ����ģ��");
		test.simulate(210, 0.25, 10);
		test.displayResults();
	}
}

/**
 * @ClassName: Customer
 * @Description:����ģ����Ҫ����Ĺ˿� 
 * @author С��ͬѧ
 * @date 2020��11��21��
 *
 */
class Customer{
	 private int arrivalTime;//�˿͵���ʱ��
	 private int transactionTime;//�˿��������ʱ��
	 private int customerNumber;//�˿����
	 public Customer(int anArrivalTime,int theTransactionTime,int anCustomerNumber) {
		 arrivalTime=anArrivalTime;
		 transactionTime=theTransactionTime;
		 customerNumber=anCustomerNumber;
	 }
	 public int getArrivalTime() {
		 return arrivalTime;
	 }
	 public int getTransactionTime() {
		 return transactionTime;
	 }
	 public int getCustomerNumber() {
		 return customerNumber;
	 }
}
