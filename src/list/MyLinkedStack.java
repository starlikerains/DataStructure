package list;

import java.util.EmptyStackException;

public class MyLinkedStack<AnyType> extends MyLinkedList<AnyType> implements MyStack<AnyType>{

	@Override
	public AnyType push(AnyType item) {
		// TODO Auto-generated method stub
		this.add(item);
		return item;
	}

	@Override
	public AnyType pop() {
		AnyType obj=peek();
		this.remove(size()-1);
		return obj;
	}

	@Override
	public AnyType peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty())throw new EmptyStackException();
		return this.get(size()-1);
	}

	@Override
	public int search(AnyType item) {
		// TODO Auto-generated method stub
		int idx=lastIndexOf(item);
		if(idx>=0)return size()-idx;
		
		return -1;
	}
}
