package bagPackage;

/**
 * BagInterface是Bag(包)的接口，定义了Bag的基本操作。Bag是一个对象容器，对象之间没有特定次序，且可以存在重复对象。
 * Bag中的对象具有相同的或相关的数据类型。
 * @ClassName: BagInterface
 * @Description:An interface that describes the operations of a bag of objects. 
 * @author 小尚同学
 * @date 2020年11月12日
 *
 */
public interface BagInterface<T> {
	/**
	 * @Title: getCurrentSize
	 * @Description:报告包中当前的对象个数
	 * @return int 包中当前的对象个数
	 * @throws 
	 */
	int getCurrentSize();
	/**
	 * @Title: isEmpty
	 * @Description: 查看包是否为空
	 * @return boolean 根据包是否为空返回真或假.
	 * @throws 
	 */
	boolean isEmpty();
	
	/**
	 * @Title: add
	 * @Description: 将给定对象添加到包中.
	 * @param newEntry newEntry是一个对象
	 * @return boolean 根据添加是否成功返回真或假
	 * @throws 添加失败异常
	 */
	boolean add(T newEntry);
	
	/**
	 * @Title: remove
	 * @Description: 从包中删除未指定的项，如果可能的话.
	 * @return T 如果删除成功则返回被删除对象的引用，否则返回null
	 * @throws BagEmptyEception
	 */
	T remove();
	
	/**
	 * @Title: remove
	 * @Description: 删除包中某个对象的一次出现，如果可能的话
	 * @param anEntry anEntry是一个对象
	 * @return boolean 根据删除是否成功返回真或假
	 * @throws 不存在此对象
	 */
	boolean remove(T anEntry);
	
	/**
	 * @Title: clear
	 * @Description: 从包中删除所有对象.
	 * @throws 
	 */
	void clear();
	
	/**
	 * @Title: getFrequencyOf
	 * @Description: 统计包中一个对象出现的次数.
	 * @param anEntry anEntry是一个对象
	 * @return 包中anEntry出现的次数
	 * @throws 
	 */
	int getFrequencyOf(T anEntry);
	/**
	 * @Title: contains
	 * @Description: 测试包是否含有某个对象.
	 * @param anEntry anEntry是一个对象
	 * @return boolean 根据包中是否含有anEntry返回真或假。
	 * @throws 
	 */
	boolean contains(T anEntry);
	
	/**
	 * @Title: toArray
	 * @Description: 查找包中所有对象.
	 * @return T[] 包中当前项的新数组。
	 * @throws 
	 */
	T[] toArray();
}
