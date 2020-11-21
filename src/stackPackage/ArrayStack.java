package stackPackage;

import java.util.Arrays;
import java.util.EmptyStackException;

public final class ArrayStack<T> implements StackInterface<T> {
	
	private T[] stack;//栈中用于存储对象的数组
	private int topIndex;//栈顶元素的索引
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY=50;
	private static final int MAX_CAPACITY=10000;
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		//The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] tempStack=(T[])new Object[initialCapacity];
		stack=tempStack;
		topIndex=-1;
		initialized=true;
	}
	
	private void checkCapacity(int capacity) throws IllegalStateException{
		if(capacity>MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a stack"+ 
											"whose capacity exceeds "+
											"allowed maximum.");
	}
	@Override
	public void push(T newEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		ensureCapacity();
		stack[topIndex+1]=newEntry;
		topIndex++;
	}
	
	private void ensureCapacity() {
		if(topIndex==stack.length-1) {
			int newLength=2*stack.length;
			checkCapacity(newLength);
			stack=Arrays.copyOf(stack, newLength);
		}
	}

	private void checkInitialization() throws SecurityException{
		if(!initialized)
			throw new SecurityException("ArrayStack object is not initialized"+
										"properly.");
	}
	@Override
	public T pop() {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else {
			T top=stack[topIndex];
			stack[topIndex]=null;
			topIndex--;
			return top;
		}
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack[topIndex];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return topIndex<0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		topIndex=-1;
	}
}
