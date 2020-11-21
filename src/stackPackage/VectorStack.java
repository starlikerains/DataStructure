package stackPackage;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * VectorStack��ʹ��Vector(����)��ʵ��StackInterface�ӿڵ�һ���ࡣ�����Ǳ�׼��Vector��ʵ����
 * ������һ����������Ϊ�����ڸ߼����飬�����Ĵ�С����������ʵ��ϸ�ڶԿͻ����ء��ڴ����У�ʹ
 * ��һ���������������������ά��ջ�е���
 * @ClassName: VectorStack
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��17��
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
