package list;

/**
 * @ClassName: MyStack
 * @Description: 栈的ADT抽象数据类型
 * @author 小尚同学
 * @date 2020年11月9日
 *
 * @param <AnyType>
 */
public interface MyStack<AnyType> {
	/**
	 * @Title: isEmpty
	 * @Description: 判断栈是否为空，是返回true，否返回false
	 * @return boolean
	 * @throws 
	 */
	boolean isEmpty();
	/**
	 * @Title: push
	 * @Description: 向栈顶添加一个元素
	 * @param item
	 * @return AnyType
	 * @throws 
	 */
	AnyType push(AnyType item);
	/**
	 * @Title: pop
	 * @Description: 弹出栈顶元素
	 * @return AnyType
	 * @throws 
	 */
	AnyType pop();
	/**
	 * @Title: peek
	 * @Description: 返回栈顶元素
	 * @return AnyType
	 * @throws 
	 */
	AnyType peek();
	/**
	 * @Title: search
	 * @Description: 从栈顶开始查找，返回item是从栈顶开始的第几个元素，若未找到，返回-1
	 * @param item
	 * @return int
	 * @throws 
	 */
	int search(AnyType item);
}
