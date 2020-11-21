package stackPackage;


/**
 * StackInterface��Stack(ջ)�Ľӿڣ�������Stack�Ļ���������Stack��һ��������ʱ����洢��������޼��ϣ�
 * ��ѭ����ȳ�(LIFO)��ԭ��Stack�洢�Ķ��������ͬ�����Ƶ��������͡�Stack���ƶ����ж���ķ��ʣ��ͻ�ֻ
 * �ܿ�����ɾ��ջ������
 * @ClassName: StackInterface
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��15��
 *
 */
public interface StackInterface<T> {
	/**
	 * @Title: push
	 * @Description:������ջ�� 
	 * @param newEntry newEntry������
	 * @throws 
	 */
	void push(T newEntry);
	/**
	 * @Title: pop
	 * @Description:ɾ��������ջ���
	 * @return ����ջ�������֮ǰ���ջ�����׳��쳣
	 * @throws StackEmptyException ����֮ǰ���ջ�����׳��쳣
	 */
	T pop();
	/**
	 * @Title: peek
	 * @Description:��ȡջ�����Ҳ��ı�ջ ��
	 * @return ����ջ������ջ�գ����׳��쳣
	 * @throws StackEmptyException ����֮ǰ���ջ�����׳��쳣
	 * 
	 */
	T peek();
	/**
	 * @Title: isEmpty
	 * @Description: ���ջ�Ƿ�Ϊ��
	 * @return ����ջ�Ƿ�Ϊ�շ�������
	 * @throws 
	 */
	boolean isEmpty();
	/**
	 * @Title: clear
	 * @Description:��ջ��ɾ�������� 
	 * @return 
	 * @throws 
	 */
	void clear();
}
