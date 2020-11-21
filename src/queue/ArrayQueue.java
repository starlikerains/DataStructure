package queue;

import exception.EmptyQueueException;

/**
 * @ClassName: ArrayQueue
 * @Description: ArrayQueue��ʹ������ʵ�ֵ�QueueInterface�������������û������Զ�����
 * @author С��ͬѧ
 * @date 2020��11��21��
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
	 * Create a new ArrayQueue����ʼ����Ϊ50
	 *
	 */
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue=(T[]) new Object[initialCapacity+1];//��һ���ռ������ж϶����Ƿ�Ϊ�գ���δʹ��
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
		if(frontIndex==((backIndex+2)%queue.length)) {//��������������ʹ��һ���ռ������ж϶����Ƿ�Ϊ��
			T[] oldQueue=queue;
			int oldSize=oldQueue.length;
			int newSize=2*oldSize;
			checkCapacity(newSize);
			@SuppressWarnings("unchecked")
			T[] tempQueue=(T[])new Object[newSize];
			queue=tempQueue;
			for(int index=0;index<oldSize-1;index++) {//����ʹ��һ���ռ������ж϶����Ƿ�Ϊ�գ�ʵ��Ԫ������ΪoldSize-1
				queue[index]=oldQueue[frontIndex];
				frontIndex=(frontIndex+1)%oldSize;
			}
			frontIndex=0;
			backIndex=oldSize-2;//ԭʼ����ʹ��һ���ռ������ж϶����Ƿ�Ϊ��
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
		while(!isEmpty()) {//Ϊ��֤���ݰ�ȫ��ѭ������dequeue���������ֵ��Ϊnull
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
	 * @Description: ��������Ƿ񳬹���������������Ļ��׳�IllegalStateException�쳣
	 * @param capacity һ��������������
	 * @throws IllegalStateException ��������������
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
	 * @Description: ����ʼ���Ƿ���ɣ�δ��ɵĻ��׳�SecurityException�쳣
	 * @throws SecurityException ��ʼ��δ���
	 * @throws 
	 */
	private void checkInitialization() throws SecurityException{
		if(!initialized)
			throw new SecurityException("ArrayBag object is not initialized"+
										"properly.");
	}
}
