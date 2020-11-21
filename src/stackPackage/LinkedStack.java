package stackPackage;

import java.util.EmptyStackException;

public final class LinkedStack<T> implements StackInterface<T> {
	
	private Node topNode;
	public LinkedStack() {
		topNode=null;
	}
	
	@Override
	public void push(T newEntry) {
		// TODO Auto-generated method stub
		topNode =new Node(newEntry,topNode);//新节点链接栈顶，成为新的栈顶，topNode指向新的栈顶
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		T top=peek();
		assert topNode!=null;
		topNode=topNode.getNextNode();
		return top;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new EmptyStackException();
		else
			return topNode.getData();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return topNode==null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		topNode=null;
	}
	
	private class Node{
		private T data;
		private Node next;
		private Node(T newEntry) {
			this(newEntry,null);
		}
		private Node(T newEntry,Node nextNode) {
			data=newEntry;
			next=nextNode;
		}
		@SuppressWarnings("unused")
		private void setNode(T newEntry,Node nextNode) {
			setData(newEntry);
			setNextNode(nextNode);
		}
		private void setData(T newEntry) {
			data=newEntry;
		}
		private T getData() {
			return data;
		}
		private void setNextNode(Node nextNode) {
			next=nextNode;
		}
		private Node getNextNode() {
			return next;
		}
	}

}
