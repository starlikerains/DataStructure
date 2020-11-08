package list;

import java.util.Iterator;

/**
 * {@code MyLinkedList}类是采用链式存储结构实现的泛型线性表，链表结构采用双链表形式。{@code MyLinkedList}实现了Iterable接口，可以调用iterator方法返回一个
 * MyLinkedList对象的迭代器，可以方便 的遍历表中的每一个元素。
 * @ClassName: MyLinkedList
 * @Description: {@code MyLinkedList}类是采用链式存储结构实现的泛型线性表。
 * @author 小尚同学
 * @date 2020年11月4日
 *
 * @param <AnyType> 任意一种已实现的数据类型
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	
	private int theSize;//表中包含元素的数量
	private int modCount=0;//表进行插入、删除、清空操作的总次数
	private Node<AnyType> beginMarker;//头结点，让对链表第一个元素的操作与对链表的其他元素的操作统一
	private Node<AnyType> endMarker;//尾结点，让在表的末尾插入元素的操作与在其他位置的操作统一；在访问超过链表二分之一位置的元素时，从尾结点出发可以提高效率
	
	/**
	 * @ClassName: Node
	 * @Description: 这是一个内部类，用于构造链表节点
	 * @author 小尚同学
	 * @date 2020年11月4日
	 *
	 * @param <AnyType> 任意一种已实现的数据类型
	 */
	private static class Node<AnyType>{
		/**
		 * 创建一个新的结点 Node
		 *
		 * @param d 数据域
		 * @param p 上一个节点的引用
		 * @param n 下一个节点的引用
		 */
		public Node(AnyType d,Node<AnyType> p,Node<AnyType> n) {
			data=d;prev=p;next=n;
		}
		public AnyType data;//数据域
		public Node<AnyType> prev;//上一个节点的引用
		public Node<AnyType> next;//下一个节点的引用
	}
	
	/**
	 * 创建一个新的实例 MyLinkedList
	 *
	 */
	public MyLinkedList() {
		clear();
	}
	
	/**
	 * @Title: clear
	 * @Description: 清空链表
	 * @throws 
	 */
	public void clear() {
		beginMarker=new Node<AnyType>(null,null,null);
		endMarker=new Node<AnyType>(null,beginMarker,null);//让尾结点的pre域指向头结点，保持双链表结构
		beginMarker.next=endMarker;//让头结点的next域指向尾结点，保持双链表结构
		
		theSize=0;//将表中包含的元素个数置为0
		modCount++;//因为链表元素数量发生了改变，所以modCount加1
	}
	
	/**
	 * @Title: size
	 * @Description: 返回链表中包含元素的个数
	 * @return int 表中元素的个数
	 * @throws 
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * @Title: isEmpty
	 * @Description: 判断链表中包含的元素个数是否为0，是返回true，否返回false
	 * @return boolean
	 * @throws 
	 */
	public boolean isEmpty() {
		return size()==0;
	}
	
	/**
	 * @Title: add
	 * @Description: 在尾结点前插入元素x(即在线性表的末尾添加一个元素）
	 * @param x
	 * @return boolean
	 * @throws 
	 */
	public boolean add(AnyType x) {
		add(size(),x);return true;//size()的值便是新节点需要插入的位置（表尾）
	}
	
	/**
	 * @Title: add
	 * @Description: 在链表的第idx+1个元素前插入一个新的元素x
	 * @param idx x要插入到表中的位置，从0开始
	 * @param x 待插入元素的引用
	 * @throws 
	 */
	public void add(int idx,AnyType x) {
		addBefore(getNode(idx),x);
	}
	
	
	/**
	 * @Title: get
	 * @Description: 获取链表第idx+1个元素的值
	 * @param idx 待获取元素在链表中的位置，从0开始
	 * @return AnyType 返回元素结点中存储的值
	 * @throws 
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}
	
	/**
	 * @Title: set
	 * @Description: 将链表第idx+1个元素的值更新为newVal
	 * @param idx 待更新元素在链表中的位置，从0开始
	 * @param newVal 更新后的值
	 * @return AnyType 返回更新前的值
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
	 * @Description: 删除链表中第idx+1个元素
	 * @param idx 待删除元素在链表中的位置，从0开始
	 * @return AnyType 返回被删除元素的值
	 * @throws 
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}
	
	/**
	 * @Title: addBefore
	 * @Description: 在p结点前插入一个值为x的新节点
	 * @param p 被插入结点的引用
	 * @param x 待插入结点的值
	 * @throws 
	 */
	private void addBefore(Node<AnyType> p,AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
		newNode.prev.next=newNode;
		p.prev=newNode;
		theSize++;
		modCount++;//链表元素数量发生改变，modCount加1
	}
	
	/**
	 * @Title: remove
	 * @Description: 删除p结点
	 * @param p 待删除的结点
	 * @return AnyType 返回被删除结点的值
	 * @throws 
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev=p.prev;//将被删除结点的上一个结点的下一个结点指向被删除结点的下一个结点，保持双链结构
		p.prev.next=p.next;//将被删除结点的下一个结点的上一个结点指向被删除结点的上一个结点，保持双链结构
		theSize--;//链表包含元素的个数减一
		modCount++;//链表元素数量发生改变，modCount加1
		return p.data;
	}
	
	
	/**
	 * @Title: getNode
	 * @Description: 返回链表内第idx+1个元素的引用
	 * @param idx 待获取元素在链表中的位置，从0开始
	 * @return Node<AnyType>
	 * @throws 
	 */
	private  Node<AnyType> getNode(int idx) {
		Node<AnyType> p;
		if(idx<0||idx>size())throw new IndexOutOfBoundsException();//判断位置索引是否合法
		if(idx<size()/2) {//为提高效率，若位置索引处于链表的前半部分，就从头结点出发向后查找
			p=beginMarker.next;
			for(int i=0;i<idx;i++) {
				p=p.next;
			}
		}else {//若位置索引处于链表的后半部分，就从尾结点出发向前查找
			p=endMarker;
			for(int i=size();i>idx;i--) {
				p=p.prev;
			}
		}
		return p;
	}
	
	/* (非 Javadoc)
	 * <p>Title: iterator</p>
	 * <p>Description: 返回一个迭代器，用于遍历链表</p>
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
	 * @Description: 构造一个链表的迭代器，用于遍历链表中的每一个元素
	 * @author 小尚同学
	 * @date 2020年11月7日
	 *
	 */
	private class LinkedListIterator implements Iterator<AnyType>{
		private Node<AnyType> current=beginMarker.next;//从链表的第一个元素开始遍历
		private int expectedModCount = modCount;//用于判断当前的迭代器是否仍然有效（若在遍历的过程中，链表元素数量发生变化，迭代器将失效，因为无法判断当前正在访问的结点是否已被删除）
		private boolean okToRemove=false;//用于判断是否可以在保证迭代器仍然有效的前提下，删除刚访问过的结点
		/* (非 Javadoc)
		 * <p>Title: hasNext</p>
		 * <p>Description:判断是否还有元素未被遍历 </p>
		 * @return
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current!=endMarker;
		}

		/* (非 Javadoc)
		 * <p>Title: next</p>
		 * <p>Description:返回下一个未被遍历的元素的值 </p>
		 * @return AnyType 下一个元素的值
		 * @see java.util.Iterator#next()
		 */
		@Override
		public AnyType next() {
			// TODO Auto-generated method stub
			if(modCount!=expectedModCount)throw new java.util.ConcurrentModificationException();//若链表元素数量发生变化，迭代器失效
			if(!hasNext())throw new java.util.NoSuchElementException();//若所有元素都已经被遍历，抛出异常
			
			AnyType nextItem=current.data;
			current=current.next;
			okToRemove=true;//此时可以确保迭代器仍然有效，且刚访问过的元素未被删除，因此将OKToRemove置为true
			return nextItem;
		}
		
		/* (非 Javadoc)
		 * <p>Title: remove</p>
		 * <p>Description: 删除刚访问过的元素</p>
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			if(modCount!=expectedModCount)throw new java.util.ConcurrentModificationException();//若链表元素发生变化，迭代器失效
			if(!okToRemove)throw new IllegalStateException();//若okToRemove处于false，删除操作将非法
			
			MyLinkedList.this.remove(current.prev);
			okToRemove=false;//刚访问过的元素已被删除，将OKToRemove置为false
			expectedModCount++;//因为已知进行过一次删除操作，expectedModcount需要加1
		}
	}
}
