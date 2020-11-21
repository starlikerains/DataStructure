package queue;

/**
 * @ClassName: DequeInterface
 * @Description:DequeInterface��Deque�Ľӿڣ�Deque��˫�˶��У������ڶ��׺Ͷ�β���в����ɾ������ 
 * @author С��ͬѧ
 * @date 2020��11��21��
 *
 * @param <T>
 */
public interface DequeInterface<T> {
	/**
	 * @Title: addToFront
	 * @Description:�ڶ���(��β)����һ��Ԫ�� 
	 * @param newEntry ��Ҫ�����Ԫ��
	 * @throws 
	 */
	public void addToFront(T newEntry);
	public void addToBack(T newEntry);
	
	/**
	 * @Title: removeFront
	 * @Description: �ڶ���(��β)�Ƴ�������һ��Ԫ��
	 * @return ���ر�ɾ����Ԫ��
	 * @throws EmptyQueueException ����ڽ��д˲���ʱ��Ϊ��
	 */
	public T removeFront();
	public T removeBack();
	
	/**
	 * @Title: getFront
	 * @Description: ���ض���(��β)Ԫ�ز����Զ��н����޸�
	 * @return ���׻��βԪ��
	 * @throws EmptyQueueException ����ڽ��д˲���ʱ��Ϊ��
	 */
	public T getFront();
	public T getBack();
	
	public boolean isEmpty();
	
	public void clear();
}
