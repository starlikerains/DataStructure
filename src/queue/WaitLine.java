package queue;


import exception.EmptyQueueException;

/**
 * WaitLine是用队列结构来模拟排队问题的类。用于求解顾客平均等待时长
 * @ClassName: WaitLine
 * @Description: WaitLine是用队列结构来模拟问题的类。
 * @author 小尚同学
 * @date 2020年11月21日
 *
 */
public class WaitLine {
	private QueueInterface<Customer> line;
	private int numberOfArrivals;//已到达人数
	private int numberServed;//已服务人数
	private int totalTimeWaited;//所有顾客总等待时长
	
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
				System.out.println("顾客 "+numberOfArrivals
								   +" 在 "+clock
								   +" 时进入. 所需服务时长为 "
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
				System.out.println("顾客"+nextCustomer.getCustomerNumber()
								   +" 在 "+clock
								   +" 时开始服务。等待时长为"+timeWaited);
			}
		}
		line.clear();
	}
	
	public void displayResults() {
		System.out.println();
		System.out.println("总服务人数= "+numberServed);
		System.out.println("总等待时长= "+totalTimeWaited);
		double averageTimeWaited=((double)totalTimeWaited)/numberServed;
		System.out.println("平均等待时长= "+averageTimeWaited);
	}
	
	public final void reset() {
		line.clear();
		numberOfArrivals=0;
		numberServed=0;
		totalTimeWaited=0;
	}
	public static void main(String[] args) throws EmptyQueueException {
		WaitLine test=new WaitLine();
		System.out.println("移动营业厅顾客排队情景模拟");
		System.out.println("营业时间为: \n\t09:00-12:00\n"+"\t14:00-17:50");
		System.out.println("上午排队情况模拟");
		test.simulate(180, 0.2, 10);
		System.out.println();
		System.out.println("下午排队情况模拟");
		test.simulate(210, 0.25, 10);
		test.displayResults();
	}
}

/**
 * @ClassName: Customer
 * @Description:用来模拟需要服务的顾客 
 * @author 小尚同学
 * @date 2020年11月21日
 *
 */
class Customer{
	 private int arrivalTime;//顾客到达时间
	 private int transactionTime;//顾客所需服务时长
	 private int customerNumber;//顾客序号
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
