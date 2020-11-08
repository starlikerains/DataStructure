package list;

import java.util.Iterator;

/**
 * {@code MyLinkedList}���ǲ�����ʽ�洢�ṹʵ�ֵķ������Ա�����ṹ����˫������ʽ��{@code MyLinkedList}ʵ����Iterable�ӿڣ����Ե���iterator��������һ��
 * MyLinkedList����ĵ����������Է��� �ı������е�ÿһ��Ԫ�ء�
 * @ClassName: MyLinkedList
 * @Description: {@code MyLinkedList}���ǲ�����ʽ�洢�ṹʵ�ֵķ������Ա�
 * @author С��ͬѧ
 * @date 2020��11��4��
 *
 * @param <AnyType> ����һ����ʵ�ֵ���������
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	
	private int theSize;//���а���Ԫ�ص�����
	private int modCount=0;//����в��롢ɾ������ղ������ܴ���
	private Node<AnyType> beginMarker;//ͷ��㣬�ö������һ��Ԫ�صĲ���������������Ԫ�صĲ���ͳһ
	private Node<AnyType> endMarker;//β��㣬���ڱ��ĩβ����Ԫ�صĲ�����������λ�õĲ���ͳһ���ڷ��ʳ����������֮һλ�õ�Ԫ��ʱ����β�������������Ч��
	
	/**
	 * @ClassName: Node
	 * @Description: ����һ���ڲ��࣬���ڹ�������ڵ�
	 * @author С��ͬѧ
	 * @date 2020��11��4��
	 *
	 * @param <AnyType> ����һ����ʵ�ֵ���������
	 */
	private static class Node<AnyType>{
		/**
		 * ����һ���µĽ�� Node
		 *
		 * @param d ������
		 * @param p ��һ���ڵ������
		 * @param n ��һ���ڵ������
		 */
		public Node(AnyType d,Node<AnyType> p,Node<AnyType> n) {
			data=d;prev=p;next=n;
		}
		public AnyType data;//������
		public Node<AnyType> prev;//��һ���ڵ������
		public Node<AnyType> next;//��һ���ڵ������
	}
	
	/**
	 * ����һ���µ�ʵ�� MyLinkedList
	 *
	 */
	public MyLinkedList() {
		clear();
	}
	
	/**
	 * @Title: clear
	 * @Description: �������
	 * @throws 
	 */
	public void clear() {
		beginMarker=new Node<AnyType>(null,null,null);
		endMarker=new Node<AnyType>(null,beginMarker,null);//��β����pre��ָ��ͷ��㣬����˫����ṹ
		beginMarker.next=endMarker;//��ͷ����next��ָ��β��㣬����˫����ṹ
		
		theSize=0;//�����а�����Ԫ�ظ�����Ϊ0
		modCount++;//��Ϊ����Ԫ�����������˸ı䣬����modCount��1
	}
	
	/**
	 * @Title: size
	 * @Description: ���������а���Ԫ�صĸ���
	 * @return int ����Ԫ�صĸ���
	 * @throws 
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * @Title: isEmpty
	 * @Description: �ж������а�����Ԫ�ظ����Ƿ�Ϊ0���Ƿ���true���񷵻�false
	 * @return boolean
	 * @throws 
	 */
	public boolean isEmpty() {
		return size()==0;
	}
	
	/**
	 * @Title: add
	 * @Description: ��β���ǰ����Ԫ��x(�������Ա��ĩβ���һ��Ԫ�أ�
	 * @param x
	 * @return boolean
	 * @throws 
	 */
	public boolean add(AnyType x) {
		add(size(),x);return true;//size()��ֵ�����½ڵ���Ҫ�����λ�ã���β��
	}
	
	/**
	 * @Title: add
	 * @Description: ������ĵ�idx+1��Ԫ��ǰ����һ���µ�Ԫ��x
	 * @param idx xҪ���뵽���е�λ�ã���0��ʼ
	 * @param x ������Ԫ�ص�����
	 * @throws 
	 */
	public void add(int idx,AnyType x) {
		addBefore(getNode(idx),x);
	}
	
	
	/**
	 * @Title: get
	 * @Description: ��ȡ�����idx+1��Ԫ�ص�ֵ
	 * @param idx ����ȡԪ���������е�λ�ã���0��ʼ
	 * @return AnyType ����Ԫ�ؽ���д洢��ֵ
	 * @throws 
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}
	
	/**
	 * @Title: set
	 * @Description: �������idx+1��Ԫ�ص�ֵ����ΪnewVal
	 * @param idx ������Ԫ���������е�λ�ã���0��ʼ
	 * @param newVal ���º��ֵ
	 * @return AnyType ���ظ���ǰ��ֵ
	 * @throws 
	 */
	public AnyType set(int idx,AnyType newVal) {
		Node<AnyType> p=getNode(idx);
		AnyType oldVal=p.data;
		p.data=newVal;
		return oldVal;
	}
	
	
	/**
	 * @Title: remove
	 * @Description: ɾ�������е�idx+1��Ԫ��
	 * @param idx ��ɾ��Ԫ���������е�λ�ã���0��ʼ
	 * @return AnyType ���ر�ɾ��Ԫ�ص�ֵ
	 * @throws 
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}
	
	/**
	 * @Title: addBefore
	 * @Description: ��p���ǰ����һ��ֵΪx���½ڵ�
	 * @param p �������������
	 * @param x ���������ֵ
	 * @throws 
	 */
	private void addBefore(Node<AnyType> p,AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
		newNode.prev.next=newNode;
		p.prev=newNode;
		theSize++;
		modCount++;//����Ԫ�����������ı䣬modCount��1
	}
	
	/**
	 * @Title: remove
	 * @Description: ɾ��p���
	 * @param p ��ɾ���Ľ��
	 * @return AnyType ���ر�ɾ������ֵ
	 * @throws 
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev=p.prev;//����ɾ��������һ��������һ�����ָ��ɾ��������һ����㣬����˫���ṹ
		p.prev.next=p.next;//����ɾ��������һ��������һ�����ָ��ɾ��������һ����㣬����˫���ṹ
		theSize--;//�������Ԫ�صĸ�����һ
		modCount++;//����Ԫ�����������ı䣬modCount��1
		return p.data;
	}
	
	
	/**
	 * @Title: getNode
	 * @Description: ���������ڵ�idx+1��Ԫ�ص�����
	 * @param idx ����ȡԪ���������е�λ�ã���0��ʼ
	 * @return Node<AnyType>
	 * @throws 
	 */
	private  Node<AnyType> getNode(int idx) {
		Node<AnyType> p;
		if(idx<0||idx>size())throw new IndexOutOfBoundsException();//�ж�λ�������Ƿ�Ϸ�
		if(idx<size()/2) {//Ϊ���Ч�ʣ���λ���������������ǰ�벿�֣��ʹ�ͷ������������
			p=beginMarker.next;
			for(int i=0;i<idx;i++) {
				p=p.next;
			}
		}else {//��λ��������������ĺ�벿�֣��ʹ�β��������ǰ����
			p=endMarker;
			for(int i=size();i>idx;i--) {
				p=p.prev;
			}
		}
		return p;
	}
	
	/* (�� Javadoc)
	 * <p>Title: iterator</p>
	 * <p>Description: ����һ�������������ڱ�������</p>
	 * @return
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
	/**
	 * @ClassName: LinkedListIterator
	 * @Description: ����һ������ĵ����������ڱ��������е�ÿһ��Ԫ��
	 * @author С��ͬѧ
	 * @date 2020��11��7��
	 *
	 */
	private class LinkedListIterator implements Iterator<AnyType>{
		private Node<AnyType> current=beginMarker.next;//������ĵ�һ��Ԫ�ؿ�ʼ����
		private int expectedModCount = modCount;//�����жϵ�ǰ�ĵ������Ƿ���Ȼ��Ч�����ڱ����Ĺ����У�����Ԫ�����������仯����������ʧЧ����Ϊ�޷��жϵ�ǰ���ڷ��ʵĽ���Ƿ��ѱ�ɾ����
		private boolean okToRemove=false;//�����ж��Ƿ�����ڱ�֤��������Ȼ��Ч��ǰ���£�ɾ���շ��ʹ��Ľ��
		/* (�� Javadoc)
		 * <p>Title: hasNext</p>
		 * <p>Description:�ж��Ƿ���Ԫ��δ������ </p>
		 * @return
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current!=endMarker;
		}

		/* (�� Javadoc)
		 * <p>Title: next</p>
		 * <p>Description:������һ��δ��������Ԫ�ص�ֵ </p>
		 * @return AnyType ��һ��Ԫ�ص�ֵ
		 * @see java.util.Iterator#next()
		 */
		@Override
		public AnyType next() {
			// TODO Auto-generated method stub
			if(modCount!=expectedModCount)throw new java.util.ConcurrentModificationException();//������Ԫ�����������仯��������ʧЧ
			if(!hasNext())throw new java.util.NoSuchElementException();//������Ԫ�ض��Ѿ����������׳��쳣
			
			AnyType nextItem=current.data;
			current=current.next;
			okToRemove=true;//��ʱ����ȷ����������Ȼ��Ч���Ҹշ��ʹ���Ԫ��δ��ɾ������˽�OKToRemove��Ϊtrue
			return nextItem;
		}
		
		/* (�� Javadoc)
		 * <p>Title: remove</p>
		 * <p>Description: ɾ���շ��ʹ���Ԫ��</p>
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			if(modCount!=expectedModCount)throw new java.util.ConcurrentModificationException();//������Ԫ�ط����仯��������ʧЧ
			if(!okToRemove)throw new IllegalStateException();//��okToRemove����false��ɾ���������Ƿ�
			
			MyLinkedList.this.remove(current.prev);
			okToRemove=false;//�շ��ʹ���Ԫ���ѱ�ɾ������OKToRemove��Ϊfalse
			expectedModCount++;//��Ϊ��֪���й�һ��ɾ��������expectedModcount��Ҫ��1
		}
	}
}
