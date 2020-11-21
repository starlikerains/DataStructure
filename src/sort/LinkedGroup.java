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
	 * @Description: ��δ����Ľ����뵽���򲿷��еĺ���λ��
	 * @param nodeToInsert δ���򲿷ֵĵ�һ���ڵ�
	 * @throws 
	 */
	private void insertInOrder(Node nodeToInsert) {
		T item=nodeToInsert.getData();
		Node currentNode=firstNode;
		Node previousNode=null;
		while((currentNode!=null)&&
				(item.compareTo(currentNode.getData())>0)) {//���׽ڵ㿪ʼ���ң��ҵ���һ��С�ڵ���item�Ľ�㣬�����������ǲ����λ��
			previousNode=currentNode;
			currentNode=currentNode.getNextNode();
		}
		if(previousNode!=null) {//���Ϊ�գ�˵����һ��������
			previousNode.setNextNode(nodeToInsert);
			nodeToInsert.setNextNode(firstNode);
		}else {
			nodeToInsert.setNextNode(firstNode);
			firstNode=nodeToInsert;
		}
	}
	/**
	 * @Title: insertionSort
	 * @Description:  �ò��������㷨ʵ�ֵ����������㷨
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
