package queue;

import exception.EmptyQueueException;

/**
 * @ClassName: QueueInterface
 * @Description: QueueInterface是Queue的接口，Queue(队列)是仅可以在一端插入，在另一端删除的一种受限的线性表
 * @author 小尚同学
 * @date 2020年11月21日
 *
 * @param <T>
 */
public interface QueueInterface<T> {
	/**
	 * @Title: enqueue
	 * @Description:在队尾添加一个新元素 
	 * @param newEntry 被添加的元素
	 * @throws 
	 */
	public void enqueue(T newEntry);
	
	/**
	 * @Title: dequeue
	 * @Description:移除并返回队首元素 
	 * @return 队首元素
	 * @throws EmptyQueueException 如果在进行此操作时队空
	 */
	public T dequeue() throws EmptyQueueException;
	
	/**
	 * @Title: getFront
	 * @Description: 返回队首元素并不对队列进行改动
	 * @return 队首元素
	 * @throws EmptyQueueException 如果在进行此操作时队空
	 */
	public T getFront() throws EmptyQueueException;
	
	/**
	 * @Title: isEmpty
	 * @Description:判读队列是否为空 
	 * @return 根据队列是否为空返回真或假
	 * @throws 
	 */
	public boolean isEmpty();
	
	/**
	 * @Title: clear
	 * @Description: 清空队列 
	 * @throws 
	 */
	public void clear();
}
