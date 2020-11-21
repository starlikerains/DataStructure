package queue;

import exception.EmptyQueueException;

/**
 * @ClassName: LinkedQueue
 * @Description:LinkedQueue是用链式存储结构实现的QueueInterface 
 * @author 小尚同学
 * @date 2020年11月21日
 *
 * @param <T>
 */
public final class LinkedQueue<T> implements QueueInterface<T>{
	private Node firstNode;
	private Node lastNode;
	public LinkedQueue() {
		firstNode=null;
		lastNode=null;
	}
	
	private class Node{
		private T data;
		private Node next;
		public Node(T anEntry,Node nextNode) {
			data=anEntry;
			next=nextNode;
		}
		public Node(T anEntry) {
			this(anEntry,null);
		}
		public T getData() {
			return data;
		}
		public void setNextNode(Node nextNode) {
			next=nextNode;
		}
		public void setData(T anEntry) {
			data=anEntry;
		}
		public Node getNextNode() {
			return next;
		}
	}
	
	@Override
	public void enqueue(T newEntry) {
		// TODO Auto-generated method stub
		Node newNode=new Node(newEntry);
		if(isEmpty()) {//如果队为空，需要将firstNode和lastNode都指向newNode
			firstNode=newNode;
		}else {
			lastNode.setNextNode(newNode);
		}
		lastNode=newNode;
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		T front=getFront();//may throw EmptyQueueException
		assert firstNode!=null;
		firstNode.setData(null);//将被删除的结点的值置为null，防止被恶意窃取
		firstNode=firstNode.getNextNode();
		if(firstNode==null)//如果firstNode为null，说明表为空，将lastNode也置为null
			lastNode=null;
		return front;
	}

	@Override
	public T getFront() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return firstNode.getData();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return firstNode==null&&lastNode==null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		firstNode=null;
		lastNode=null;
	}
	
}
