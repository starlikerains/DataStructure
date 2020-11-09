package list;

public interface MyList<AnyType>{
	int size();
	void clear();
	boolean isEmpty();
	AnyType get(int idx);
	AnyType set(int idx,AnyType newVal);
	boolean add(AnyType x);
	void add(int idx,AnyType x);
	AnyType remove(int idx);
	String toString();
}
