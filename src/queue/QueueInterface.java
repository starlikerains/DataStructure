package queue;

import exception.EmptyQueueException;

/**
 * @ClassName: QueueInterface
 * @Description: QueueInterface��Queue�Ľӿڣ�Queue(����)�ǽ�������һ�˲��룬����һ��ɾ����һ�����޵����Ա�
 * @author С��ͬѧ
 * @date 2020��11��21��
 *
 * @param <T>
 */
public interface QueueInterface<T> {
	/**
	 * @Title: enqueue
	 * @Description:�ڶ�β���һ����Ԫ�� 
	 * @param newEntry ����ӵ�Ԫ��
	 * @throws 
	 */
	public void enqueue(T newEntry);
	
	/**
	 * @Title: dequeue
	 * @Description:�Ƴ������ض���Ԫ�� 
	 * @return ����Ԫ��
	 * @throws EmptyQueueException ����ڽ��д˲���ʱ�ӿ�
	 */
	public T dequeue() throws EmptyQueueException;
	
	/**
	 * @Title: getFront
	 * @Description: ���ض���Ԫ�ز����Զ��н��иĶ�
	 * @return ����Ԫ��
	 * @throws EmptyQueueException ����ڽ��д˲���ʱ�ӿ�
	 */
	public T getFront() throws EmptyQueueException;
	
	/**
	 * @Title: isEmpty
	 * @Description:�ж������Ƿ�Ϊ�� 
	 * @return ���ݶ����Ƿ�Ϊ�շ�������
	 * @throws 
	 */
	public boolean isEmpty();
	
	/**
	 * @Title: clear
	 * @Description: ��ն��� 
	 * @throws 
	 */
	public void clear();
}
