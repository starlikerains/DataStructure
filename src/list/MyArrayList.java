package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code MyArrayList}����һ������˳��洢�ṹʵ�ֵķ������Ա����Ա�������������ű�Ԫ���������Ӷ���̬����(ÿ����һ��������Ҫ�ƶ�����Ԫ��һ��,Ч�ʽϵ�)��
  *  ��֪�����Ա���Ҫ����N��ǰ���£����Ե���ensureCapture���������Ա�����������N��������������������ƶ�Ԫ�����Ч�ʽ��͡���ȷ�����Ա��������Ԫ�غ󣬿���
  *  ����trimToSize�������ն����������{@code MyArrayList}��ʵ����Iterable�ӿڣ���˿��Ե���iterator������ȡһ��{@code MyArrayList}����ĵ�����������
  *  �ķ���������е�ÿһ��Ԫ�ء�
 * @ClassName: MyArrayList
 * @Description: {@code MyArrayList}����һ������˳��洢�ṹʵ�ֵķ������Ա�
 * @author С��ͬѧ
 * @date 2020��11��4��
 * @param <AnyType> �������κ��Ѷ������������
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>,MyList<AnyType> {
	private static final int DEFAULT_CAPACITY = 10;//Ĭ������
	private int theSize;//��Ĵ�С
	private AnyType [] theItems;//���÷�������洢Ԫ��
	
	/**
	 * ����һ���յ�MyArrayList
	 *
	 */
	public MyArrayList() {
		clear();
	}
	
	/**
	 * @Title: clear
	 * @Description: ���MyArrayList�������С��Ϊ0��������Ϊ��ʼ����
	 * @return void ��������
	 */
	public void clear() {
		theSize=0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	/**
	 * @Title: size
	 * @Description: ����MyArrayList�Ĵ�С
	 * @return int ��Ĵ�С
	 * @throws
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * @Title: isEmpty
	 * @Description: �ж�MyArrayList�Ĵ�С�Ƿ�Ϊ0���Ƿ���true�����򷵻�false
	 * @return boolean �Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return theSize==0;
	}
	
	
	/**
	 * @Title: trimToSize
	 * @Description: ���ձ���δʹ�õĿռ�
	 */
	public void trimToSize() {
		ensureCapacity(size());
	}
	
	/**
	 * @Title: get
	 * @Description: ��ȡ���еĵ�idx+1��Ԫ��
	 * @param idx Ԫ���ڱ��е��±�
	 * @return AnyType ���ظ�Ԫ�ص�����
	 * @throws ArrayIndexOutOfBoundsException Խ�����
	 */
	public AnyType get(int idx) {
		if(idx<0||idx>=size())throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}
	
	/**
	 * @Title: set
	 * @Description: �����еĵ�idx+1��Ԫ�ظ���ΪnewVal�������ظ�λ��ԭ��Ԫ��oldVal������
	 * @param idx Ԫ���ڱ��е��±�
	 * @param newVal ��Ԫ�ص�����
	 * @return AnyType ���ظ�λ��ԭ��Ԫ��oldVal������
	 * @throws ArrayIndexOutOfBoundsException Խ�����
	 */
	public AnyType set(int idx,AnyType newVal) {
		if(idx<0||idx>=size())throw new ArrayIndexOutOfBoundsException();
		AnyType old=theItems[idx];
		theItems[idx]=newVal;
		return old;
	}
	
	/**
	 * @Title: ensureCapacity
	 * @Description: ��MyArrayList����������ΪnewCapacity����ԭ��������newCapacity���������κθĶ�
	 * @param newCapacity �������Ĵ�С
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity) {
		if(newCapacity<theSize) {
			return;
		}
		AnyType [] old = theItems;
		theItems=(AnyType[])new Object[newCapacity];
		for(int i=0;i<size();i++) {
			theItems[i]=old[i];
		}
	}
	
	/**
	 * @Title: add
	 * @Description: �ڱ��ĩβ���Ԫ��x
	 * @param x ����ӵ�Ԫ��x
	 * @return boolean ����true 
	 * @throws 
	 */
	public boolean add(AnyType x) {
		add(size(),x);
		return true;
	}
	
	/**
	 * @Title: add
	 * @Description: �����еĵ�idx+1��λ�õ�Ԫ�ؼ�֮���Ԫ����������ƶ�һ��λ�ã����ڱ�ĵ�idx+1��λ�ò���Ԫ��x��
	 * @param idx �����Ԫ�ص�λ���±�
	 * @param x ������Ԫ�ص�����
	 * @throws 
	 */
	public void add(int idx,AnyType x) {
		if(theItems.length==size()) {
			ensureCapacity(size()*2+1);
		}
		for(int i=theSize;i>idx;i--) {
			theItems[i]=theItems[i-1];
		}
		theItems[idx]=x;
		theSize++;
	}
	
	/**
	 * @Title: remove
	 * @Description: ɾ�����е�idx+1��λ�õ�Ԫ�أ���λ��֮���Ԫ��������ǰ�ƶ�һ��λ��
	 * @param idx ��ɾ��Ԫ�ص�λ���±�
	 * @return AnyType ���ر�ɾ��Ԫ�ص�����
	 * @throws 
	 */
	public AnyType remove(int idx) {
		AnyType removeItem=theItems[idx];
		for(int i=idx;i<size()-1;i++) {
			theItems[i]=theItems[i+1];
		}
		theSize--;
		return removeItem;
	}
	
	/* (�� Javadoc)
	 * <p>Title: iterator</p>
	 * <p>Description: ���ظ�MyArrayList����ĵ�����</p>
	 * @return ���ص�����������
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	
	/**
	 * @ClassName: ArrayListIterator
	 * @Description: ����һ���ڲ��࣬���ڹ���MyArrayList�ĵ�����
	 * @author С��ͬѧ
	 * @date 2020��11��4��
	 *
	 */
	private class ArrayListIterator implements Iterator<AnyType>{
		private int current=0;//������������Ԫ���ڱ��е�λ��
		/* (�� Javadoc)
		 * <p>Title: hasNext</p>
		 * <p>Description: �ж��Ƿ���Ԫ��δ���������Ƿ���true���񷵻�false</p>
		 * @return
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current<size();
		}
		
		@Override
		public AnyType next() {
			// TODO Auto-generated method stub
			if(!hasNext())throw new NoSuchElementException();
			return theItems[current++];
		}
		
		public void remove() {
			// TODO ɾ����next���·��ص���(�˺����ǲ����ٵ���remove��ֱ����next��һ�ε���֮��
			MyArrayList.this.remove(--current);
		}
	}
	
	/**
	 * @Title: IndexOf
	 * @Description: ���ر��г��ֵĵ�һ��x���±꣬�����в�����x������-1
	 * @param x
	 * @return int
	 * @throws 
	 */
	public int IndexOf(AnyType x) {
		for(int i=0;i<size();i++) {
			if(theItems[i].equals(x))return i;
		}
		return -1;
	}
	
	/**
	 * @Title: lastIndexOf
	 * @Description: ���ر��г��ֵ����һ��x���±꣬�����в�����x������-1
	 * @param x
	 * @return int
	 * @throws 
	 */
	public int lastIndexOf(AnyType x) {
		for(int i=size()-1;i>=0;i--) {
			if(theItems[i].equals(x))return i;
		}
		return -1;
	}
	
	/* (�� Javadoc)
	 * <p>Title: toString</p>
	 * <p>Description: ��MyArrayList�����е�����Ԫ�ص�ֵ����"[0,1,2...]"��ʽ������ӽ�һ���ַ�����������</p>
	 * @return String �����ַ���
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Iterator<AnyType> it=iterator();
		if(!it.hasNext()) {
			return "[]";
		}
		StringBuilder sb=new StringBuilder();
		sb.append('[');
		for(;;) {
			AnyType e=it.next();
			sb.append(e==this?"(this Collection":e);
			if(!it.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(',').append(' ');
		}
	}

}
