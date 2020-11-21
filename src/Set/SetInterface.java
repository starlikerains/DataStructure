package Set;


/**
 * @ClassName: SetInterface
 * @Description:һ��������������(Set)�����Ľӿ� 
 * @author С��ͬѧ
 * @date 2020��11��13��
 *
 */
public interface SetInterface<T> {
	int getCurrentSize();
	boolean isEmpty();
	/**
	 * @Title: add
	 * @Description:������������ӵ������У����������ظ��� 
	 * @param newEntry newEntry��һ������
	 * @return ��ӳɹ�����true������������Ѿ�����������󣬷���false��
	 * @throws 
	 */
	boolean add(T newEntry);
	/**
	 * @Title: remove
	 * @Description: �Ӽ�����ɾ��ָ������������ܵĻ�
	 * @param anEntry anEntry��һ������
	 * @return boolean ����ɾ���ɹ���񷵻����
	 * @throws 
	 */
	boolean remove(T anEntry);
	T remove();
	void clear();
	boolean contains(T anEntry);
	T[] toArray();
}
