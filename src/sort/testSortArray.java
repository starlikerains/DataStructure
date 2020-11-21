package sort;

import java.util.Arrays;

public class testSortArray {
	private static <T> void showArray(T[] anArray){
		StringBuilder result=new StringBuilder();
		result.append('[');
		for(int i=0;i<anArray.length;i++) {			
			result.append(anArray[i].toString());
			char cnt=i!=anArray.length-1?' ':']';
			result.append(cnt);
		}
		System.out.println(result.toString());
	}
	private static <T extends Comparable<? super T>> void testSelectionSort(T[] a,int first,int last) {
		T[] temp=Arrays.copyOf(a, a.length);
		System.out.println("ԭ����Ϊ:");
		showArray(temp);
		SortArray.selectionSort(temp, first, last);
		System.out.println("ѡ������Ľ��Ϊ��");
		showArray(temp);
	}
	
	private static <T extends Comparable<? super T>> void testInsertionSort(T[] a,int first,int last) {
		T[] temp=Arrays.copyOf(a, a.length);
		System.out.println("ԭ����Ϊ:");
		showArray(temp);
		SortArray.insertionSort(temp, first, last);
		System.out.println("��������Ľ��Ϊ��");
		showArray(temp);
	}
	
	private static <T extends Comparable<? super T>> void testSheelSort(T[] a,int first,int last) {
		T[] temp=Arrays.copyOf(a, a.length);
		System.out.println("ԭ����Ϊ:");
		showArray(temp);
		SortArray.sheelSort(temp, first, last);
		System.out.println("ϣ������Ľ��Ϊ��");
		showArray(temp);
	}
	
	private static <T extends Comparable<? super T>> void testMergeSort(T[] a,int first,int last) {
		T[] temp=Arrays.copyOf(a, a.length);
		System.out.println("ԭ����Ϊ:");
		showArray(temp);
		SortArray.mergeSort(temp, first, last);
		System.out.println("�鲢����Ľ��Ϊ��");
		showArray(temp);
	}
	
	private static <T extends Comparable<? super T>> void testQuickSort(T[] a,int first,int last) {
		T[] temp=Arrays.copyOf(a, a.length);
		System.out.println("ԭ����Ϊ:");
		showArray(temp);
		SortArray.quickSort(temp, first, last);
		System.out.println("��������Ľ��Ϊ��");
		showArray(temp);
	}
	public static void main(String[] args) {
		Integer[] a=new Integer[] {4,7,2,9,8,5,6,3,1};
		testSelectionSort(a,0,a.length-1);
		testInsertionSort(a,0,a.length-1);
		testSheelSort(a,0,a.length-1);
		testMergeSort(a,0,a.length-1);
		testQuickSort(a,0,a.length-1);
	}
}
