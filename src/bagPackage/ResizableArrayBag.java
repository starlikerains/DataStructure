package bagPackage;

import java.util.Arrays;

/**
 * ResizableArrayBag是对BagInterface的数组实现。与ArrayBag不同，ResizableArrayBag可以动态扩充容量，
 * 只要未达到最大容量和内存未被分配完，Bag的容量都可以继续进行扩充。
 * @ClassName: ResizableArrayBag
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 * @param <T>
 */
public final class ResizableArrayBag<T> implements BagInterface<T> {
	private T[] bag;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY=25;
	private boolean initialized=false;
	private static final int MAX_CAPACITY=10000;

	/**
	 * Create an empty bag whose capacity is 25.
	 *
	 */
	public ResizableArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Create an empty bag having a given capacity.
	 *
	 * @param capacity The integer capacity desired.
	 */
	public ResizableArrayBag(int desiredCapacity) {
		checkCapacity(desiredCapacity);//需要检查容量是否超过最大容量
		//The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] tempBag=(T[])new Object[desiredCapacity];//Unchecked cast
		bag=tempBag;
		numberOfEntries=0;
		initialized=true;//last action		
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

	@Override
	public boolean add(T newEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		if(isArrayFull()) {
			doubleCapacity();
		}
		//Assertion:result is true here.
		bag[numberOfEntries]=newEntry;
		numberOfEntries++;
		return true;
	}
	
	/**
	 * @Title: checkCapacity
	 * @Description: 
	 * @param capacity 
	 * @throws IllegalStateException
	 */
	private void checkCapacity(int capacity) throws IllegalStateException{
		if(capacity>MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag"+ 
											"whose capacity exceeds "+
											"allowed maximum.");
	}
	
	/**
	 * @Title: doubleCapacity
	 * @Description:  将容量扩充为原来的一倍，实现方式是申请一个容量加倍的新数组，然后将原数组
	 * 存储的所有对象的引用复制到新数组中，由于数组存储的是对象的引用，这个过程不会特别耗时。
	 * @throws 
	 */
	private void doubleCapacity() {
		int newLength=2*bag.length;
		checkCapacity(newLength);//检查容量是否超过最大容量
		bag=Arrays.copyOf(bag, newLength);
	}
	
	
	//Returns true if the bag is full,or false if not.
	private boolean isArrayFull() {
		return numberOfEntries>=bag.length;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		checkInitialization();
		T result=removeEntry(numberOfEntries-1);
		return result;
	}

	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		int index=getIndexOf(anEntry);
		T result=removeEntry(index);
		return anEntry.equals(result);
	}
	
	//Removes and returns the entry at a given array index.
	//If no such entry exists,return null.
	//Preconditions:0<=givenIndex<numberOfEntries;
	//				checkInitialization has been called.
	private T removeEntry(int givenIndex) {
		T result=null;
		if(!isEmpty()&&(givenIndex>=0)) {
			result=bag[givenIndex];
			bag[givenIndex]=bag[numberOfEntries-1];
			bag[numberOfEntries-1]=null;
			numberOfEntries--;
		}
		return result;
	}
	
	private int getIndexOf(T anEntry) {
		int where=-1;
		boolean strillLooking=true;
		int index=0;
		while(!strillLooking&&(index<numberOfEntries)) {
			if(anEntry.equals(bag[index])) {
				strillLooking=false;
				where=index;
			}
			index++;
		}
		return where;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty()) {
			remove();
		}
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		int counter=0;
		for(int index=0;index<numberOfEntries;index++) {
			if(anEntry.equals(bag[index])) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		return getIndexOf(anEntry)>-1;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		checkInitialization();
		//The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] result=(T[])new Object[numberOfEntries];
		for(int index=0;index<numberOfEntries;index++) {
			result[index]=bag[index];
		}
		return result;
	}
	
	//Throws an exception if this object is not initialized.
	/**
	 * @Title: checkInitialization
	 * @Description:  
	 * @throws SecurityException 安全异常，对象未完成初始化
	 */
	private void checkInitialization() throws SecurityException{
		if(!initialized)
			throw new SecurityException("ArrayBag object is not initialized"+
										"properly.");
	}
}
