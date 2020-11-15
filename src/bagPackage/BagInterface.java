package bagPackage;

/**
 * BagInterface��Bag(��)�Ľӿڣ�������Bag�Ļ���������Bag��һ����������������֮��û���ض������ҿ��Դ����ظ�����
 * Bag�еĶ��������ͬ�Ļ���ص��������͡�
 * @ClassName: BagInterface
 * @Description:An interface that describes the operations of a bag of objects. 
 * @author С��ͬѧ
 * @date 2020��11��12��
 *
 */
public interface BagInterface<T> {
	/**
	 * @Title: getCurrentSize
	 * @Description:������е�ǰ�Ķ������
	 * @return int ���е�ǰ�Ķ������
	 * @throws 
	 */
	int getCurrentSize();
	/**
	 * @Title: isEmpty
	 * @Description: �鿴���Ƿ�Ϊ��
	 * @return boolean ���ݰ��Ƿ�Ϊ�շ�������.
	 * @throws 
	 */
	boolean isEmpty();
	
	/**
	 * @Title: add
	 * @Description: ������������ӵ�����.
	 * @param newEntry newEntry��һ������
	 * @return boolean ��������Ƿ�ɹ���������
	 * @throws ���ʧ���쳣
	 */
	boolean add(T newEntry);
	
	/**
	 * @Title: remove
	 * @Description: �Ӱ���ɾ��δָ�����������ܵĻ�.
	 * @return T ���ɾ���ɹ��򷵻ر�ɾ����������ã����򷵻�null
	 * @throws BagEmptyEception
	 */
	T remove();
	
	/**
	 * @Title: remove
	 * @Description: ɾ������ĳ�������һ�γ��֣�������ܵĻ�
	 * @param anEntry anEntry��һ������
	 * @return boolean ����ɾ���Ƿ�ɹ���������
	 * @throws �����ڴ˶���
	 */
	boolean remove(T anEntry);
	
	/**
	 * @Title: clear
	 * @Description: �Ӱ���ɾ�����ж���.
	 * @throws 
	 */
	void clear();
	
	/**
	 * @Title: getFrequencyOf
	 * @Description: ͳ�ư���һ��������ֵĴ���.
	 * @param anEntry anEntry��һ������
	 * @return ����anEntry���ֵĴ���
	 * @throws 
	 */
	int getFrequencyOf(T anEntry);
	/**
	 * @Title: contains
	 * @Description: ���԰��Ƿ���ĳ������.
	 * @param anEntry anEntry��һ������
	 * @return boolean ���ݰ����Ƿ���anEntry�������١�
	 * @throws 
	 */
	boolean contains(T anEntry);
	
	/**
	 * @Title: toArray
	 * @Description: ���Ұ������ж���.
	 * @return T[] ���е�ǰ��������顣
	 * @throws 
	 */
	T[] toArray();
}
