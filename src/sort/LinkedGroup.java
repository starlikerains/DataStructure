package sort;

public class LinkedGroup<T extends Comparable<? super T>> {
	private Node firstNode;
	int length;
	private class Node{
		private T data;
		private Node next;
		public Node(T anEntry,Node nodeNext) {
			data=anEntry;
			next=nodeNext;
		}
		public void setData(T anEntry) {
			data=anEntry;
		}
		public void setNextNode(Node nodeNext) {
			next=nodeNext;
		}
		public T getData() {
			return data;
		}
		public Node getNextNode() {
			return next;
		}
	}
	/**
	 * @Title: insertInOrder
	 * @Description: 将未排序的结点插入到有序部分中的合适位置
	 * @param nodeToInsert 未排序部分的第一个节点
	 * @throws 
	 */
	private void insertInOrder(Node nodeToInsert) {
		T item=nodeToInsert.getData();
		Node currentNode=firstNode;
		Node previousNode=null;
		while((currentNode!=null)&&
				(item.compareTo(currentNode.getData())>0)) {//从首节点开始查找，找到第一个小于等于item的结点，这个结点后面就是插入的位置
			previousNode=currentNode;
			currentNode=currentNode.getNextNode();
		}
		if(previousNode!=null) {//如果为空，说明是一个空链表
			previousNode.setNextNode(nodeToInsert);
			nodeToInsert.setNextNode(firstNode);
		}else {
			nodeToInsert.setNextNode(firstNode);
			firstNode=nodeToInsert;
		}
	}
	/**
	 * @Title: insertionSort
	 * @Description:  用插入排序算法实现的链表排序算法
	 * @throws 
	 */
	public void insertionSort() {
		if(length>1) {
			assert firstNode!=null;
			Node unsortedPart=firstNode.getNextNode();
			assert unsortedPart!=null;
			firstNode.setNextNode(null);
			while(unsortedPart!=null) {
				Node nodeToInsert=unsortedPart;
				unsortedPart=unsortedPart.getNextNode();
				insertInOrder(nodeToInsert);
			}
		}
	}
	
	
}
