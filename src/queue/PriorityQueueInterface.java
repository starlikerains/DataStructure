package queue;

/**
 * 优先队列根据元素的优先级来组织元素先后次序，优先级高的元素处在优先级低的元素的前方，优先级相同的元素根据时间先后顺序进行排队
 * @ClassName: PriorityQueueInterface
 * @Description: PriorityQueueInterface是PriorityQueue(优先队列)的接口。优先队列根据元素的优先级来组织元素先后次序
 * @author 小尚同学
 * @date 2020年11月21日
 *
 * @param <T>
 */
public interface PriorityQueueInterface<T extends Comparable<? super T>>{
	/**
	 * @Title: add
	 * @Description: 在优先队列中添加一个元素
	 * @param newEntry 被添加的元素
	 * @throws 
	 */
	public void add(T newEntry);
	/**
	 * @Title: remove
	 * @Description: 移除并返回优先队列队首元素
	 * @return 队首元素，如果队列为空，返回null
	 * @throws 
	 */
	public T remove();
	/**
	 * @Title: peek
	 * @Description:返回优先队列队首元素并不对队列进行改变 
	 * @return 队首元素，如果队列为空，返回null
	 * @throws 
	 */
	public T peek();
	public boolean isEmpty();
	/**
	 * @Title: getSize
	 * @Description: 返回优先队列中包含的元素数量
	 * @return 一个整数表示队列中包含元素的数量
	 * @throws 
	 */
	public int getSize();
	public void clear();
	
}
