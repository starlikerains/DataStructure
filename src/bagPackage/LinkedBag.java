package bagPackage;

/**
 * LinkedBag��BagInterface����ʽʵ�֡�
 * @ClassName: LinkedBag
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��15��
 *
 * @param <T>
 */
public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;//ָ���׽ڵ�
	private int numberOfEntries;
	
	public LinkedBag() {
		firstNode=null;
		numberOfEntries=0;
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

	/*
	 * ʹ��ͷ�巨��ӽ��
	 */
	@Override
	public boolean add(T newEntry) {
		firstNode=new Node(newEntry,firstNode);//������������׽ڵ㣬��Ϊ�µ��׽ڵ㣬firstNodeָ���µ��׽ڵ�
		numberOfEntries++;
		return true;
	}

	/*
	 * ɾ��firstNodeָ��Ľ��
	 */
	@Override
	public T remove() {
		T result=null;
		if(firstNode!=null) {
			result=firstNode.data;
			firstNode=firstNode.next;
			numberOfEntries--;
		}
		return result;
	}

	/*
	 * ���ڰ��еĶ��󲢲������ض����򣬹ʿ���ͨ�����׽ڵ��Ԫ��ת�Ƶ���Ҫ��ɾ���Ľ���У�
	 * Ȼ��ɾ���׽ڵ�
	 */
	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		boolean result=false;
		Node nodeN=getReferenceTo(anEntry);
		if(nodeN!=null) {
			nodeN.data=firstNode.data;
			firstNode=firstNode.next;
			numberOfEntries--;
			result=true;
		}
		return result;
	}
	
	/**
	 * @Title: getReferenceTo
	 * @Description: ��λһ������ָ������Ľ���λ��
	 * @param anEntry 
	 * @return ��λ�ɹ������ذ����ö���Ľ������ã����򷵻�null
	 * @throws 
	 */
	private Node getReferenceTo(T anEntry) {
		boolean found=false;
		Node currentNode=firstNode;
		while(!found&&(currentNode!=null)) {
			if(anEntry.equals(currentNode.data))
				found=true;
			else
				currentNode=currentNode.next;
		}
		return currentNode;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty())
			remove();
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		// TODO Auto-generated method stub
		int frequency=0;
		//����ʹ��loopCounter������index�Խ����м�������Ϊ�����������飬��㲢û���±�
		int loopCounter=0;
		Node currentNode=firstNode;
		while((loopCounter<numberOfEntries)&&(currentNode!=null)) {
			if(anEntry.equals(currentNode.data))
				frequency++;
			loopCounter++;
			currentNode=currentNode.next;
		}
		return frequency;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return getReferenceTo(anEntry)!=null;
	}

	@Override
	public T[] toArray() {
		//The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] result=(T[])new Object[numberOfEntries];//Unchecked cast
		int index=0;
		Node currentNode=firstNode;
		//currentNode��ָ�����н������ã��ڷ���������ǰ��Ҫȷ��currentNode����null
		//����ᷢ��NullPointerException
		while((index<numberOfEntries)&&(currentNode!=null)) {
			result[index]=currentNode.data;
			index++;
			currentNode=currentNode.next;
		}
		return result;
	}

	/*
	 * node��LinkedBag�������洢����Ľ�㣬��ͬ���֮����������γ�����ṹ��
	 * ��Ϊnode��LinkedBag���ڲ�ʵ�֣�Ӧ�öԿͻ����أ���˽�node����Ϊ�ڲ��࣬
	 * ʹ����ⲻ�ɼ������Ҳ����Ҫ���÷���(set)�ͷ��ʷ���(get)��
	 */
	private class Node{
		private T data;//bag�еĶ���
		private Node next;//���ӵ���һ��node
		private Node(T dataPortion) {
			this(dataPortion,null);
		}
		private Node(T dataPortion,Node nextNode) {
			data=dataPortion;
			next=nextNode;
		}
	}
}
