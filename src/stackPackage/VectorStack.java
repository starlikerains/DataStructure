package stackPackage;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * VectorStack是使用Vector(向量)来实现StackInterface接口的一个类。向量是标准类Vector的实例，
 * 向量是一个对象，其行为类似于高级数组，向量的大小按需增长，实现细节对客户隐藏。在此类中，使
 * 用一个向量对象和向量方法来维护栈中的项
 * @ClassName: VectorStack
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月17日
 *
 * @param <T>
 */
public final class VectorStack<T> implements StackInterface<T> {
	private Vector<T> stack;
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY=50;
	private static final int MAX_CAPACITY=10000;
	
	public VectorStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public VectorStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		stack=new Vector<>(initialCapacity);
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
		stack.add(newEntry);
	}
	private void checkInitialization() throws SecurityException{
		if(!initialized)
			throw new SecurityException("VectorStack object is not initialized"+
										"properly.");
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack.remove(stack.size()-1);
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack.lastElement();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		stack.clear();
	}

}
