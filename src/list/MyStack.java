package list;

/**
 * @ClassName: MyStack
 * @Description: ջ��ADT������������
 * @author С��ͬѧ
 * @date 2020��11��9��
 *
 * @param <AnyType>
 */
public interface MyStack<AnyType> {
	/**
	 * @Title: isEmpty
	 * @Description: �ж�ջ�Ƿ�Ϊ�գ��Ƿ���true���񷵻�false
	 * @return boolean
	 * @throws 
	 */
	boolean isEmpty();
	/**
	 * @Title: push
	 * @Description: ��ջ�����һ��Ԫ��
	 * @param item
	 * @return AnyType
	 * @throws 
	 */
	AnyType push(AnyType item);
	/**
	 * @Title: pop
	 * @Description: ����ջ��Ԫ��
	 * @return AnyType
	 * @throws 
	 */
	AnyType pop();
	/**
	 * @Title: peek
	 * @Description: ����ջ��Ԫ��
	 * @return AnyType
	 * @throws 
	 */
	AnyType peek();
	/**
	 * @Title: search
	 * @Description: ��ջ����ʼ���ң�����item�Ǵ�ջ����ʼ�ĵڼ���Ԫ�أ���δ�ҵ�������-1
	 * @param item
	 * @return int
	 * @throws 
	 */
	int search(AnyType item);
}
