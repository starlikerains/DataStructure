package Bag;

public class ArrayBag<T> implements BagInterface<T>{
	private final T[] bag;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY=25;
	private boolean initialized=false;
	private static final int MAX_CAPACITY=10000;

	/**
	 * Create an empty bag whose capacity is 25.
	 *
	 */
	public ArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Create an empty bag having a given capacity.
	 *
	 * @param capacity The integer capacity desired.
	 */
	public ArrayBag(int desiredCapacity) {
		//The cast is safe because the new array contains null entries.
		if(desiredCapacity<=MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempBag=(T[])new Object[desiredCapacity];//Unchecked cast
			bag=tempBag;
			numberOfEntries=0;
			initialized=true;//last action
		}else
			throw new IllegalStateException("Attempt to create a bag"+ 
											"whose capacity exceeds "+
											"allowed maximum.");
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
		boolean result=true;
		if(isArrayFull()) {
			result=false;
		}else {
			//Assertion:result is true here.
			bag[numberOfEntries]=newEntry;
			numberOfEntries++;
		}
		return result;
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
		boolean found=false;
		int index=0;
		while(!found&&(index<numberOfEntries)) {
			if(anEntry.equals(bag[index])) {
				found=true;
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
	private void checkInitialization() {
		if(!initialized)
			throw new SecurityException("ArrayBag object is not initialized"+
										"properly.");
	}
}
