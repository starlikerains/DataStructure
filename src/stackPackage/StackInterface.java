package stackPackage;


/**
 * StackInterface是Stack(栈)的接口，定义了Stack的基本操作。Stack是一个按逆向时间序存储对象的有限集合，
 * 遵循后进先出(LIFO)的原则，Stack存储的对象具有相同或相似的数据类型。Stack限制对其中对象的访问，客户只
 * 能看到或删除栈顶对象。
 * @ClassName: StackInterface
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 */
public interface StackInterface<T> {
	/**
	 * @Title: push
	 * @Description:添加新项到栈顶 
	 * @param newEntry newEntry是新项
	 * @throws 
	 */
	void push(T newEntry);
	/**
	 * @Title: pop
	 * @Description:删除并返回栈顶项。
	 * @return 返回栈顶项。操作之前如果栈空则抛出异常
	 * @throws StackEmptyException 操作之前如果栈空则抛出异常
	 */
	T pop();
	/**
	 * @Title: peek
	 * @Description:获取栈顶项且不改变栈 。
	 * @return 返回栈顶项。如果栈空，则抛出异常
	 * @throws StackEmptyException 操作之前如果栈空则抛出异常
	 * 
	 */
	T peek();
	/**
	 * @Title: isEmpty
	 * @Description: 检查栈是否为空
	 * @return 根据栈是否为空返回真或假
	 * @throws 
	 */
	boolean isEmpty();
	/**
	 * @Title: clear
	 * @Description:从栈中删除所有项 
	 * @return 
	 * @throws 
	 */
	void clear();
}
