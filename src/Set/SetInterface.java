package Set;


/**
 * @ClassName: SetInterface
 * @Description:一个描述集合类型(Set)操作的接口 
 * @author 小尚同学
 * @date 2020年11月13日
 *
 */
public interface SetInterface<T> {
	int getCurrentSize();
	boolean isEmpty();
	/**
	 * @Title: add
	 * @Description:将给定对象添加到集合中，对象不允许重复。 
	 * @param newEntry newEntry是一个对象。
	 * @return 添加成功返回true，如果集合中已经存在这个对象，返回false。
	 * @throws 
	 */
	boolean add(T newEntry);
	/**
	 * @Title: remove
	 * @Description: 从集合中删除指定对象，如果可能的话
	 * @param anEntry anEntry是一个对象
	 * @return boolean 根据删除成功与否返回真假
	 * @throws 
	 */
	boolean remove(T anEntry);
	T remove();
	void clear();
	boolean contains(T anEntry);
	T[] toArray();
}
