package queue;

/**
 * @ClassName: DequeInterface
 * @Description:DequeInterface是Deque的接口，Deque是双端队列，可以在队首和队尾进行插入和删除操作 
 * @author 小尚同学
 * @date 2020年11月21日
 *
 * @param <T>
 */
public interface DequeInterface<T> {
	/**
	 * @Title: addToFront
	 * @Description:在队首(队尾)插入一个元素 
	 * @param newEntry 需要插入的元素
	 * @throws 
	 */
	public void addToFront(T newEntry);
	public void addToBack(T newEntry);
	
	/**
	 * @Title: removeFront
	 * @Description: 在队首(队尾)移除并返回一个元素
	 * @return 返回被删除的元素
	 * @throws EmptyQueueException 如果在进行此操作时队为空
	 */
	public T removeFront();
	public T removeBack();
	
	/**
	 * @Title: getFront
	 * @Description: 返回队首(队尾)元素并不对队列进行修改
	 * @return 队首或队尾元素
	 * @throws EmptyQueueException 如果在进行此操作时队为空
	 */
	public T getFront();
	public T getBack();
	
	public boolean isEmpty();
	
	public void clear();
}
