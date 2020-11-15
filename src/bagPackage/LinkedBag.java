package bagPackage;

/**
 * LinkedBag是BagInterface的链式实现。
 * @ClassName: LinkedBag
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 * @param <T>
 */
public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;//指向首节点
	private int numberOfEntries;
	
	public LinkedBag() {
		firstNode=null;
		numberOfEntries=0;
	}
	
	@Override
	public int getCurrentSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries==0;
	}

	/*
	 * 使用头插法添加结点
	 */
	@Override
	public boolean add(T newEntry) {
		firstNode=new Node(newEntry,firstNode);//新增结点链接首节点，成为新的首节点，firstNode指向新的首节点
		numberOfEntries++;
		return true;
	}

	/*
	 * 删除firstNode指向的结点
	 */
	@Override
	public T remove() {
		T result=null;
		if(firstNode!=null) {
			result=firstNode.data;
			firstNode=firstNode.next;
			numberOfEntries--;
		}
		return result;
	}

	/*
	 * 由于包中的对象并不存在特定次序，故可以通过将首节点的元素转移到需要被删除的结点中，
	 * 然后删除首节点
	 */
	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		boolean result=false;
		Node nodeN=getReferenceTo(anEntry);
		if(nodeN!=null) {
			nodeN.data=firstNode.data;
			firstNode=firstNode.next;
			numberOfEntries--;
			result=true;
		}
		return result;
	}
	
	/**
	 * @Title: getReferenceTo
	 * @Description: 定位一个包含指定对象的结点的位置
	 * @param anEntry 
	 * @return 定位成功，返回包含该对象的结点的引用，否则返回null
	 * @throws 
	 */
	private Node getReferenceTo(T anEntry) {
		boolean found=false;
		Node currentNode=firstNode;
		while(!found&&(currentNode!=null)) {
			if(anEntry.equals(currentNode.data))
				found=true;
			else
				currentNode=currentNode.next;
		}
		return currentNode;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty())
			remove();
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		// TODO Auto-generated method stub
		int frequency=0;
		//我们使用loopCounter而不是index对结点进行计数，因为链表并不是数组，结点并没有下标
		int loopCounter=0;
		Node currentNode=firstNode;
		while((loopCounter<numberOfEntries)&&(currentNode!=null)) {
			if(anEntry.equals(currentNode.data))
				frequency++;
			loopCounter++;
			currentNode=currentNode.next;
		}
		return frequency;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return getReferenceTo(anEntry)!=null;
	}

	@Override
	public T[] toArray() {
		//The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] result=(T[])new Object[numberOfEntries];//Unchecked cast
		int index=0;
		Node currentNode=firstNode;
		//currentNode是指向链中结点的引用，在访问数据域前，要确定currentNode不是null
		//否则会发生NullPointerException
		while((index<numberOfEntries)&&(currentNode!=null)) {
			result[index]=currentNode.data;
			index++;
			currentNode=currentNode.next;
		}
		return result;
	}

	/*
	 * node是LinkedBag中用来存储对象的结点，不同结点之间进行链接形成链表结构，
	 * 因为node是LinkedBag的内部实现，应该对客户隐藏，因此将node设置为内层类，
	 * 使其对外不可见，因而也不需要设置方法(set)和访问方法(get)。
	 */
	private class Node{
		private T data;//bag中的对象
		private Node next;//链接到下一个node
		private Node(T dataPortion) {
			this(dataPortion,null);
		}
		private Node(T dataPortion,Node nextNode) {
			data=dataPortion;
			next=nextNode;
		}
	}
}
