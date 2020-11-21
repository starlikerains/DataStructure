package queue;

/**
 * ���ȶ��и���Ԫ�ص����ȼ�����֯Ԫ���Ⱥ�������ȼ��ߵ�Ԫ�ش������ȼ��͵�Ԫ�ص�ǰ�������ȼ���ͬ��Ԫ�ظ���ʱ���Ⱥ�˳������Ŷ�
 * @ClassName: PriorityQueueInterface
 * @Description: PriorityQueueInterface��PriorityQueue(���ȶ���)�Ľӿڡ����ȶ��и���Ԫ�ص����ȼ�����֯Ԫ���Ⱥ����
 * @author С��ͬѧ
 * @date 2020��11��21��
 *
 * @param <T>
 */
public interface PriorityQueueInterface<T extends Comparable<? super T>>{
	/**
	 * @Title: add
	 * @Description: �����ȶ��������һ��Ԫ��
	 * @param newEntry ����ӵ�Ԫ��
	 * @throws 
	 */
	public void add(T newEntry);
	/**
	 * @Title: remove
	 * @Description: �Ƴ����������ȶ��ж���Ԫ��
	 * @return ����Ԫ�أ��������Ϊ�գ�����null
	 * @throws 
	 */
	public T remove();
	/**
	 * @Title: peek
	 * @Description:�������ȶ��ж���Ԫ�ز����Զ��н��иı� 
	 * @return ����Ԫ�أ��������Ϊ�գ�����null
	 * @throws 
	 */
	public T peek();
	public boolean isEmpty();
	/**
	 * @Title: getSize
	 * @Description: �������ȶ����а�����Ԫ������
	 * @return һ��������ʾ�����а���Ԫ�ص�����
	 * @throws 
	 */
	public int getSize();
	public void clear();
	
}
