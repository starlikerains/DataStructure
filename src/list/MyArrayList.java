package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code MyArrayList}类是一个采用顺序存储结构实现的泛型线性表，线性表的容量可以随着表元素数量增加而动态增加(每扩大一次容量需要移动所有元素一次,效率较低)。
  *  在知道线性表需要容量N的前提下，可以调用ensureCapture方法将线性表容量扩大至N，避免因扩大容量多次移动元素造成效率降低。在确定线性表不会再添加元素后，可以
  *  调用trimToSize方法回收多余的容量。{@code MyArrayList}类实现了Iterable接口，因此可以调用iterator方法获取一个{@code MyArrayList}对象的迭代器，可以
  *  的方便遍历表中的每一个元素。
 * @ClassName: MyArrayList
 * @Description: {@code MyArrayList}类是一个采用顺序存储结构实现的泛型线性表。
 * @author 小尚同学
 * @date 2020年11月4日
 * @param <AnyType> 可以是任何已定义的数据类型
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>,MyList<AnyType> {
	private static final int DEFAULT_CAPACITY = 10;//默认容量
	private int theSize;//表的大小
	private AnyType [] theItems;//采用泛型数组存储元素
	
	/**
	 * 构建一个空的MyArrayList
	 *
	 */
	public MyArrayList() {
		clear();
	}
	
	/**
	 * @Title: clear
	 * @Description: 清空MyArrayList，将表大小置为0，容量置为初始容量
	 * @return void 返回类型
	 */
	public void clear() {
		theSize=0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	/**
	 * @Title: size
	 * @Description: 返回MyArrayList的大小
	 * @return int 表的大小
	 * @throws
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * @Title: isEmpty
	 * @Description: 判断MyArrayList的大小是否为0，是返回true，否则返回false
	 * @return boolean 是否为空
	 */
	public boolean isEmpty() {
		return theSize==0;
	}
	
	
	/**
	 * @Title: trimToSize
	 * @Description: 回收表中未使用的空间
	 */
	public void trimToSize() {
		ensureCapacity(size());
	}
	
	/**
	 * @Title: get
	 * @Description: 获取表中的第idx+1个元素
	 * @param idx 元素在表中的下标
	 * @return AnyType 返回该元素的引用
	 * @throws ArrayIndexOutOfBoundsException 越界错误
	 */
	public AnyType get(int idx) {
		if(idx<0||idx>=size())throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}
	
	/**
	 * @Title: set
	 * @Description: 将表中的第idx+1个元素更改为newVal，并返回该位置原来元素oldVal的引用
	 * @param idx 元素在表中的下标
	 * @param newVal 新元素的引用
	 * @return AnyType 返回该位置原来元素oldVal的引用
	 * @throws ArrayIndexOutOfBoundsException 越界错误
	 */
	public AnyType set(int idx,AnyType newVal) {
		if(idx<0||idx>=size())throw new ArrayIndexOutOfBoundsException();
		AnyType old=theItems[idx];
		theItems[idx]=newVal;
		return old;
	}
	
	/**
	 * @Title: ensureCapacity
	 * @Description: 将MyArrayList的容量扩大为newCapacity，若原容量大于newCapacity，将不做任何改动
	 * @param newCapacity 新容量的大小
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity) {
		if(newCapacity<theSize) {
			return;
		}
		AnyType [] old = theItems;
		theItems=(AnyType[])new Object[newCapacity];
		for(int i=0;i<size();i++) {
			theItems[i]=old[i];
		}
	}
	
	/**
	 * @Title: add
	 * @Description: 在表的末尾添加元素x
	 * @param x 需添加的元素x
	 * @return boolean 返回true 
	 * @throws 
	 */
	public boolean add(AnyType x) {
		add(size(),x);
		return true;
	}
	
	/**
	 * @Title: add
	 * @Description: 将表中的第idx+1个位置的元素及之后的元素依次向后移动一个位置，并在表的第idx+1个位置插入元素x，
	 * @param idx 需插入元素的位置下标
	 * @param x 待插入元素的引用
	 * @throws 
	 */
	public void add(int idx,AnyType x) {
		if(theItems.length==size()) {
			ensureCapacity(size()*2+1);
		}
		for(int i=theSize;i>idx;i--) {
			theItems[i]=theItems[i-1];
		}
		theItems[idx]=x;
		theSize++;
	}
	
	/**
	 * @Title: remove
	 * @Description: 删除表中第idx+1个位置的元素，该位置之后的元素依次向前移动一个位置
	 * @param idx 需删除元素的位置下标
	 * @return AnyType 返回被删除元素的引用
	 * @throws 
	 */
	public AnyType remove(int idx) {
		AnyType removeItem=theItems[idx];
		for(int i=idx;i<size()-1;i++) {
			theItems[i]=theItems[i+1];
		}
		theSize--;
		return removeItem;
	}
	
	/* (非 Javadoc)
	 * <p>Title: iterator</p>
	 * <p>Description: 返回该MyArrayList对象的迭代器</p>
	 * @return 返回迭代器的引用
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	
	/**
	 * @ClassName: ArrayListIterator
	 * @Description: 这是一个内部类，用于构造MyArrayList的迭代器
	 * @author 小尚同学
	 * @date 2020年11月4日
	 *
	 */
	private class ArrayListIterator implements Iterator<AnyType>{
		private int current=0;//即将被遍历的元素在表中的位置
		/* (非 Javadoc)
		 * <p>Title: hasNext</p>
		 * <p>Description: 判断是否还有元素未被遍历，是返回true，否返回false</p>
		 * @return
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current<size();
		}
		
		@Override
		public AnyType next() {
			// TODO Auto-generated method stub
			if(!hasNext())throw new NoSuchElementException();
			return theItems[current++];
		}
		
		public void remove() {
			// TODO 删除由next最新返回的项(此后我们不能再调用remove，直到对next再一次调用之后
			MyArrayList.this.remove(--current);
		}
	}
	
	/**
	 * @Title: IndexOf
	 * @Description: 返回表中出现的第一个x的下标，若表中不存在x，返回-1
	 * @param x
	 * @return int
	 * @throws 
	 */
	public int IndexOf(AnyType x) {
		for(int i=0;i<size();i++) {
			if(theItems[i].equals(x))return i;
		}
		return -1;
	}
	
	/**
	 * @Title: lastIndexOf
	 * @Description: 返回表中出现的最后一个x的下标，若表中不存在x，返回-1
	 * @param x
	 * @return int
	 * @throws 
	 */
	public int lastIndexOf(AnyType x) {
		for(int i=size()-1;i>=0;i--) {
			if(theItems[i].equals(x))return i;
		}
		return -1;
	}
	
	/* (非 Javadoc)
	 * <p>Title: toString</p>
	 * <p>Description: 将MyArrayList对象中的所有元素的值按照"[0,1,2...]"格式依次添加进一个字符串，并返回</p>
	 * @return String 返回字符串
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Iterator<AnyType> it=iterator();
		if(!it.hasNext()) {
			return "[]";
		}
		StringBuilder sb=new StringBuilder();
		sb.append('[');
		for(;;) {
			AnyType e=it.next();
			sb.append(e==this?"(this Collection":e);
			if(!it.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(',').append(' ');
		}
	}

}
