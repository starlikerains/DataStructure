package queue;

import exception.EmptyQueueException;

/**
 * @ClassName: ArrayQueue
 * @Description: ArrayQueue是使用数组实现的QueueInterface，队列容量随用户需求自动扩充
 * @author 小尚同学
 * @date 2020年11月21日
 *
 * @param <T>
 */
public final class ArrayQueue<T> implements QueueInterface<T> {
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY=50;
	private static final int MAX_CAPACITY=10000;
	
	/**
	 * Create a new ArrayQueue，初始容量为50
	 *
	 */
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue=(T[]) new Object[initialCapacity+1];//有一个空间用于判断队列是否为空，并未使用
		queue=tempQueue;
		frontIndex=0;
		backIndex=initialCapacity;
		initialized=true;
	}
	@Override
	public void enqueue(T newEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		ensureCapacity();
		backIndex=(backIndex+1)%queue.length;
		queue[backIndex]=newEntry;
	}
	private void ensureCapacity() {
		if(frontIndex==((backIndex+2)%queue.length)) {//队满条件，由于使用一个空间用于判断队列是否为空
			T[] oldQueue=queue;
			int oldSize=oldQueue.length;
			int newSize=2*oldSize;
			checkCapacity(newSize);
			@SuppressWarnings("unchecked")
			T[] tempQueue=(T[])new Object[newSize];
			queue=tempQueue;
			for(int index=0;index<oldSize-1;index++) {//由于使用一个空间用于判断队列是否为空，实际元素数量为oldSize-1
				queue[index]=oldQueue[frontIndex];
				frontIndex=(frontIndex+1)%oldSize;
			}
			frontIndex=0;
			backIndex=oldSize-2;//原始队列使用一个空间用于判断队列是否为空
		}
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty())
			throw new EmptyQueueException();
		else {
			T front=queue[frontIndex];
			queue[frontIndex]=null;
			frontIndex=(frontIndex+1)%queue.length;
			return front;
		}
	}

	@Override
	public T getFront() throws EmptyQueueException {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty()) 
			throw new EmptyQueueException();
		else 
			return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return frontIndex==((backIndex+1))%queue.length;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty()) {//为保证数据安全，循环调用dequeue将所有项的值置为null
			try {
				dequeue();
			} catch (EmptyQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Title: checkCapacity
	 * @Description: 检查容量是否超过最大容量，超出的话抛出IllegalStateException异常
	 * @param capacity 一个整数代表容量
	 * @throws IllegalStateException 如果超出最大容量
	 * @throws 
	 */
	private void checkCapacity(int capacity) throws IllegalStateException{
		if(capacity>MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a queue"+ 
											"whose capacity exceeds "+
											"allowed maximum.");
	}
	/**
	 * @Title: checkInitialization
	 * @Description: 检查初始化是否完成，未完成的话抛出SecurityException异常
	 * @throws SecurityException 初始化未完成
	 * @throws 
	 */
	private void checkInitialization() throws SecurityException{
		if(!initialized)
			throw new SecurityException("ArrayBag object is not initialized"+
										"properly.");
	}
}
