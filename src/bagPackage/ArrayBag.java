package bagPackage;

/**
 * ArrayBag是BagInterface的数组实现，即采用顺序存储结构实现。ArrayBag的容量在初始化之后就被确定了，
 * 不能对容量进行扩充。
 * @ClassName: ArrayBag
 * @Description: ArrayBag是使用数组实现的BagInterface。
 * @author 小尚同学
 * @date 2020年11月15日
 *
 * @param <T>
 */
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
		checkInitialization();//对数组进行操作，需要进行初始化检查
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

	
	/*
	 *  (非 Javadoc)
	 * 由于包中的对象不存在特定次序，且remove操作的定义是删除未指定的对象
	 * 因此在实现的时候，每次都删除数组中的最后一个对象，符合定义
	 * <p>Title: remove</p>
	 * <p>Description: </p>
	 * @return
	 * @see Bag.BagInterface#remove()
	 */
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
	
	/*
	 * 由于包中的对象之间不存在特定顺序，在删除一个对象后，可以直接用数组中的最后一个对象
	 * 进行填补，这样就不用移动被删除对象后的所有对象，时间复杂度为O(1)
	 */
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
		checkInitialization();//对数组进行操作，需要进行初始化检查
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
		checkInitialization();//对数组进行操作，需要进行初始化检查
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
	/*
	 * 由于黑客可能使用未完成初始化的对象对未初始化好的数组进行操作，造成恶意破坏，
	 * 因此使checkInitialization来检查对象是否初始化成功来规避这一风险。
	 */
	//Throws an exception if this object is not initialized.
	private void checkInitialization() {
		if(!initialized)
			throw new SecurityException("ArrayBag object is not initialized"+
										"properly.");
	}
}
