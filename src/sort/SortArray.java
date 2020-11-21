package sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * SortArray�����˶��ֲ�ͬ���������򷽷�������ѡ�����򡢲��������ϣ�������������򷽷�
 * ��Ϊ���о�̬����������ֱ��ʹ����������
 * @ClassName: SortArray
 * @Description: SortArray��һ�����ڽ����������������࣬�����ж��ֲ�ͬ���������򷽷� 				 
 * @author С��ͬѧ
 * @date 2020��11��19��
 *
 */
public class SortArray {
	/*
	 * Java�����﷨��<T extends Comparable<T>>����������T�������޶���T����Ϊʵ����Comparable�ӿڵ���
	 * 				Comparable<? super T>�еģ���ͨ���������������������ͣ�<? super T>��ʾT�����⸸
	 * 				��,�ۺ���������T����Ϊʵ������������ΪT�����⸸���Comparable�ӿڵ���
	 */
	/*
	 * ������Ҫ������a������������a�а���n��Ԫ�ء�����ѡ�������˼���Ƕ��������n�β��ң�
	 *��i�β��Ҵ������i��Ԫ�ؿ�ʼ���ҵ�ֵ��С��Ԫ�أ�����͵�i��Ԫ�ؽ�������n�β��ҽ�����
	 *����������ɡ� 
	 */
	/**
	 * @Title: selectionSort
	 * @Description: ʹ�õ���ѡ�������㷨ʵ�ֵ����������㷨��
	 * @param <T> T����Ϊʵ������������ΪT�����⸸���Comparable�ӿڵ���
	 * @param a �����������
	 * @param n ���������Ԫ������
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] a,int n) {
		for(int index=0;index<n-1;index++) {
			int indexOfNextSmallest=getIndexOfSmallest(a,index,n-1);
			swap(a,index,indexOfNextSmallest);
		}
	}
	
	/**
	 * @Title: selectionSort
	 * @Description: ʹ�õݹ�ѡ�������㷨�������ָ��Ƭ�ν�����������
	 * @param <T>
	 * @param a �����������
	 * @param first Ƭ����ʼλ��
	 * @param last Ƭ�ν���λ��
	 * @throws 
	 */
	public static<T extends Comparable<? super T>> void selectionSort(T[] a,int first,int last) {
		if(first<last) {
			int indexOfNextSmallest=getIndexOfSmallest(a,first,last);
			swap(a,first,indexOfNextSmallest);
			selectionSort(a,first+1,last);
		}
	}

	/**
	 * @Title: getIndexOfSmallest
	 * @Description:��������ָ��Ƭ���е���Сֵ�������±� 
	 * @param <T>
	 * @param a ��Ҫ���ҵ�����
	 * @param first ������ʼλ��
	 * @param last ���ҽ���λ��
	 * @return ��Сֵ�������±�
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> int getIndexOfSmallest(T[] a,int first,int last) {
		T min=a[first];
		int indexOfMin=first;
		for(int index=first+1;index<=last;index++) {
			if(a[index].compareTo(min)<0) {
				min=a[index];
				indexOfMin=index;
			}
		}
		return indexOfMin;
	}
	
	//��������Ԫ��a[i]��a[j]
	private static void swap(Object[] a,int i,int j) {
		Object temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	/**
	 * ������������ָ�Ϊ�������֣���һ������������ģ���ʼʱ�����������еĵ�һ�
	 * �ڶ������ֺ����������㷨��δ���򲿷����ߵ�һ��������������򲿷��к���
	 * ������λ�á�
	 * @Title: insertionSort
	 * @Description: ʹ�õ������������㷨�������ָ��Ƭ�ν�����������
	 * @param <T>
	 * @param a ��Ҫ���������
	 * @param first Ƭ�ο�ʼλ��
	 * @param last Ƭ�ν���λ��
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] a,int first,int last) {
		for(int unsorted=first+1;unsorted<=last;unsorted++) {
			T nextToInsert=a[unsorted];
			insertInOrder(nextToInsert,a,first,unsorted-1);//��δ���򲿷ֵĵ�һ����뵽���򲿷ֵĺ���λ��
		}
	}
	
	/**
	 * @Title: insertInOrder
	 * @Description: ��anEntry���뵽���򲿷ֵĺ���λ��
	 * @param <T>
	 * @param anEntry ��һ��δ�������Ķ���
	 * @param a ��Ҫ���������
	 * @param begin ���򲿷ֵ���ʼλ��
	 * @param end ���򲿷ֵĽ���λ��
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void insertInOrder(T anEntry,T[] a,int begin,int end) {
		int index=end;
		while((index>=begin)&&(anEntry.compareTo(a[index])<0)) {//�����򲿷ִӺ���ǰ���ң��ҵ���һ��С�ڵ���anEntry��ֵ�����ֵ����һ��λ�þ��ǲ����λ��
			a[index+1]=a[index];//Ϊ�����ڳ�λ��
			index--;
		}
		a[index+1]=anEntry;//����
		
	}
	
	/*
	 *  ϣ�������ǲ�������ı��֡����ݲ��������ڶԻ�����������н�������ʱ��ʱ�临�ӶȽӽ���O(n)���ص㣬
	 * ϣ�������Ƚ�����ת��Ϊ������������У�����ٶ��������һ����ͨ�Ĳ�������������� �������ǣ�
	 * �Ƚ����з�Ϊspace�飬ÿһ����Ԫ��֮�䲽��Ϊspace�������У�����Щ���������н��в�������ͨ����
	 * ����Сspace��ֵ��ʹ�����л������򡣵����spaceΪ1ʱ��ϣ��������Ϊ����ͨ�Ĳ�������
	 * ʵ���ϣ�ֻҪ���space��ֵΪ1��ϣ��������ܹ����������á������������е�space���ж��ܹ�ʹϣ����
	 * ��ӵ�и�Ч�ʡ�ϣ�������㷨������Shell���飬space�ĳ�ʼֵȡ���鳤�ȵ�һ�룬֮��space���ϼ���ֱ��
	 * Ϊ1��
	 */
	/**
	 * sheelSort��ʹ��ϣ�������㷨˼����������򷽷������Զ�ָ���������Ƭ�ν�������
	 * @Title: sheelSort
	 * @Description: ʹ��ϣ�������㷨���������Ƭ�ν�����������
	 * @param <T>
	 * @param a �����������
	 * @param first ��Ƭ�ε���ʼλ��
	 * @param last ��Ƭ�εĽ���λ��
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void sheelSort(T[] a,int first,int last) {
		int n=last-first+1;
		int space=n/2;
		while(space>0) {
			for(int begin=first;begin<first+space;begin++) {
				incrementalInsertionSort(a,begin,last,space);
			}
			space=space/2;
		}
	}
	
	/**
	 * @Title: incrementalInsertionSort
	 * @Description:ϣ������������򲿷֣�������a����firstΪ�ף�����Ϊspace�������н��в������� 
	 * @param <T>
	 * @param a ��Ҫ���������
	 * @param first �����п�ʼԪ�ص��±�
	 * @param last �������һ��Ԫ�ص��±�
	 * @param space ������Ԫ��֮��Ĳ���
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void incrementalInsertionSort(T[] a,int first,int last,int space) {
		for(int unsorted=first+space;unsorted<=last;unsorted+=space) {//��������������firstΪ�ף�spaceΪ�����������У�����first����Ϊfirst�Ѿ�����
			T nextToInsert=a[unsorted];//δ���򲿷ֵĵ�һ��Ԫ��
			int index=unsorted-space;//���򲿷ֵ����һ��Ԫ��
			while((index>=first)&&(nextToInsert.compareTo(a[index])<0)) {//�Ӻ���ǰ���ң��ҵ���һ��С�ڵ���nextToInsert��Ԫ�أ����Ԫ�صĺ�һ��λ�ñ��ǲ���λ��
				a[index+space]=a[index];
				index=index-space;//Ϊ�����ڳ�λ��
			}
			a[index+space]=nextToInsert;//����Ԫ��
		}
	}
	
	/*
	 * �鲢���������˷��ε�˼�룬�������Ϊ���룬�ֱ�������������Ȼ�����������򲿷ֺϲ�����һ����ʱ�����С�
	 * ͨ���ݹ���ù鲢�����Ի��ֳ����������ֽ�������
	 */
	/**
	 * mergeSort��ʹ�ù鲢�����㷨�����������ķ���
	 * @Title: mergeSort
	 * @Description: ʹ�ù鲢���򷽷���һ������Ƭ�ν�������
	 * @param <T>
	 * @param a ������Ƭ�����ڵ�����
	 * @param first Ƭ�ε���ʼλ��
	 * @param last Ƭ�εĽ���λ��
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] a,int first,int last) {
		@SuppressWarnings("unchecked")
		T[] tempArray=(T[])new Comparable<?>[a.length];
		mergeSort(a,tempArray,first,last);
	}
	
	/**
	 * @Title: mergeSort
	 * @Description: ʹ�õݹ鷽ʽʵ�ֵĹ鲢���򣬿��Զ�һ������Ƭ�ν�����������
	 * @param <T>
	 * @param a ��Ҫ���������
	 * @param tempArray �ݴ�����
	 * @param first Ƭ�ε���ʼλ��
	 * @param last Ƭ�εĽ���λ��
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void mergeSort(T[] a,T[] tempArray,int first,int last) {
		if(first<last) {
			int mid=(first+last)/2;
			mergeSort(a,tempArray,first,mid);
			mergeSort(a,tempArray,mid+1,last);
			merge(a,tempArray,first,mid,last);
		}
	}
	
//	private static <T extends Comparable<? super T>> void iterateMergeSort(T[] a,T[] tempArray,int first,int last) {
//		int mergeLength=2;
//		while(mergeLength<=a.length) {
//			for(int mergeBegin=0;mergeBegin<a.length;mergeBegin+=mergeLength) {
//				int mergeEnd,mid;
//				if((mergeBegin+mergeLength-1)<a.length) {
//					mergeEnd=mergeBegin+mergeLength-1;
//					mid=(mergeBegin+mergeEnd)/2;
//				}else {
//					mergeEnd=a.length-1;
//					mid=mergeBegin+(mergeLength/2)-1; 
//				}
//				merge(a,tempArray,mergeBegin,mid,mergeEnd);
//			}
//			mergeLength*=2;
//		}
//	}
	
	
	/**
	 * @Title: merge
	 * @Description: ���������������򲿷ֽ��кϲ� 
	 * @param <T>
	 * @param a ��Ҫ�������������
	 * @param tempArray �ݴ�����
	 * @param first ��һ�����򲿷ֵ���ʼλ��
	 * @param mid ��һ�����򲿷ֵĽ���λ��
	 * @param last �ڶ������򲿷ֵĽ���λ��
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void merge(T[] a,T[] tempArray,int first,int mid,int last) {
		int beginHalf1=first;
		int endHalf1=mid;
		int beginHalf2=mid+1;
		int endHalf2=last;
		int index=0;
		while((beginHalf1<=endHalf1)&&(beginHalf2<=endHalf2)) {//�������������������Ԫ�ؽ���鲢����ʱ����
			if(a[beginHalf1].compareTo(a[beginHalf2])<=0) {
				tempArray[index]=a[beginHalf1];
				beginHalf1++;
			}else {
				tempArray[index]=a[beginHalf2];
				beginHalf2++;
			}
			index++;
		}
		//assert:һ���������Ѿ�ȫ�����Ƶ�tempArray��
		
		//����һ����������ʣ�����Ƶ�tempArray��
		while(beginHalf1<=endHalf1) {
			tempArray[index++]=a[beginHalf1++];
		}
		while(beginHalf2<=endHalf2) {
			tempArray[index++]=a[beginHalf2++];
		}
		//assert:����while���ֻ��һ�������
		index=0;
		for(int i=first;i<=last;i++) {//��tempArray�е�ֵ���ƻ�a�����еĶ�ӦƬ��
			a[i]=tempArray[index++];
		}
	}
	
	/**
	 * �����������黮��Ϊ�����֣�����������У���������ѡ�������е�һ��׳����ᣩ���������飬���㣺
	 * 				����������λ�þ��������������е�����λ��
	 * 				����ǰ���С�ڻ��������
	 * 				����������ڻ��������
	 * ������г�Ϊ����Ļ��֡��������ֽ������Ϊ�������֣���Ϊ��С���ֺͽϴ󲿷֣�����������ֿ�������
	 * �Խ�С���ֺͽϴ󲿷�ʹ�ÿ������������������ԭʼ���齫������ġ�
	 * @Title: quickSort
	 * @Description:�ÿ��������㷨����������� 
	 * @param <T>
	 * @param a ��Ҫ���������
	 * @param first ����Ƭ�ε���ʼλ��
	 * @param last ����Ƭ�εĽ���λ��
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] a,int first,int last) {
		if(last-first+1<4) {//�ڵ��û��ַ���ǰ��Ҫ����������Ҫ��4��Դˣ�С��4�������ʹ�ò�������
			insertionSort(a,first,last);
		}else {
			int pivotIndex=partition(a,first,last);//��������л��֣���ȡ������±�
			quickSort(a,first,pivotIndex-1);//�Խ�С���ֽ�������
			quickSort(a,pivotIndex+1,last);//�Խϴ󲿷ֽ�������
		}
	}
	
	/**
	 * ��Ԫ��ֵ����ѡ�񷨣����Ա��������������ʱ�临�Ӷȡ�
	 * @Title: sortFirstMiddleLast
	 * @Description: ������ĵ�һ��м�����һ����±괫��������������Ὣλ���м��±괦��
	 * @param <T>
	 * @param a �����������
	 * @param first ������Ƭ�εĵ�һ��
	 * @param mid ������Ƭ�ε��м���
	 * @param last ������Ƭ�ε����һ��
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a,int first,int mid,int last) {
		if(a[first].compareTo(a[mid])>0) {
			swap(a,first,mid);
		}
		if(a[mid].compareTo(a[last])>0) {
			swap(a,mid,last);
		}
		if(a[first].compareTo(a[mid])>0) {
			swap(a,first,mid);
		}
	}
	
	
	/**
	 * ���ֵĹ���Ϊ��������ͷ��ʼ��������Ϊǰ�������ҵ�һ�����ڻ�����������±�ΪindexFromLeft����
	 * ���Ƶķ�ʽ���ӵ����ڶ��ʼ������ͷǰ�������ҵ�һ��С�ڻ�����������±�ΪindexFromRight����
	 * ��indexFromLeftС��indexFromRight���򽻻�������������������Һʹ���������в��ң�ֱ��indexFro
	 * mLeft��С��indexFromRight����󽻻�indexFromLeft��last��
	 * @Title: partition
	 * @Description:������Ƭ�ε��м�����Ϊ���ᣬʹ����ǰ���С�ڻ�������ᣬ����������ڻ�������ᣬ��������Ԫ�������������±꣬�Ӷ���������л���
	 * @param <T>
	 * @param a ��Ҫ���������
	 * @param first Ƭ�ο�ʼ��λ��
	 * @param last Ƭ�ν�����λ��
	 * @return �������������������±�
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> int partition(T[] a,int first,int last) {
		int mid=(first+last)/2;
		//������Ƭ�εĵ�һ��м�������һ���������ʹ�����������λ�������м���Ա��������������ʱ�临�Ӷ�
		sortFirstMiddleLast(a,first,mid,last);
		swap(a,mid,last-1);			
		int pivotIndex=last-1;//�������ڵ�λ��			
		T pivotValue=a[pivotIndex];//�����ֵ
		int indexFromLeft=first+1;
		int indexFromRight=last-2;
		boolean done=false;
		while(!done) {
			while(a[indexFromLeft].compareTo(pivotValue)<0)//�������ҵ���һ�����ڵ��������ֵ���±�
				indexFromLeft++;
			while(a[indexFromRight].compareTo(pivotValue)>0)//���ҵ����ҵ���һ��С�ڵ��������ֵ���±�
				indexFromRight--;
			if(indexFromLeft<indexFromRight) {
				swap(a,indexFromLeft,indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}else {
				done=true;
			}
		}
		swap(a,pivotIndex,indexFromLeft);
		pivotIndex=indexFromLeft;
		return pivotIndex;
	}
	
	/**
	 * @Title: radixSort
	 * @Description: ʹ�û��������������������������
	 * @param a ���������������
	 * @param first ����Ƭ�ε���ʼλ��
	 * @param last ����Ƭ�εĽ���λ��
	 * @param maxDigiths �����������λ��
	 * @throws 
	 */
	public static void radixSort(int[] a,int first,int last) {
		@SuppressWarnings("unchecked")
		Deque<Integer>[] bucket =(ArrayDeque<Integer>[]) new Object[10];//�������Ľ��ƴ���Ͱ  
		int maxDights=getMaxDights(a,first,last);//ȡ�����������ֵ��λ��
		for(int i=1;i<=maxDights;i++) {//�����ĵ�λ����λ�������������
			for(int j=0;j<10;j++) {//������е�Ͱ
				bucket[j].clear();
			}
			for(int index=first;index<=last;index++) {//����indexλ��i������ӽ���i��Ͱ
				int dight=getDightAt(a[index],i);
				bucket[dight].add(a[index]);
			}
			for(int j=0;j<10;j++) {//��10��Ͱ�е�Ԫ�ذ���0..9��˳����ӻ�ԭ��������
				int index=0;
				while(!bucket[j].isEmpty()) {
					a[index++]=bucket[j].poll();
				}
			}
		}
	}
	
	/**
	 * @Title: getMaxDights
	 * @Description:��������Ƭ�������ֵ��λ�� 
	 * @param a
	 * @param first
	 * @param last
	 * @return 
	 * @throws 
	 */
	private static int getMaxDights(int[] a,int first,int last) {
		int max=a[first];
		for(int index=first+1;index<=last;index++) {
			if(max<a[index])max=a[index];
		}
		int result=0;
		while(max>0) {
			result++;
			max/=10;
		}
		return result;
	}
	
	/**
	 * @Title: getDightAt
	 * @Description:�ӵ�λ���λ������ȡһ�������ĵ�indexλ��ֵ����������ڸ�λ������0 
	 * @param num ��һ������
	 * @param index ��Ҫ��ȡ��λ
	 * @return ������indexλ��ֵ����������ڸ�λ������0
	 * @throws 
	 */
	private static int getDightAt(int num,int index) {
		int result;
		int cnt = 0;
		int count=0;
		while(num>0&&count<index) {
			cnt=num%10;
			num/=10;
			count++;
		}
		if(count<index) {
			result=0;
		}else {
			result=cnt;
		}
		return result;
	}
}
