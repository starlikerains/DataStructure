package sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * SortArray包含了多种不同的数组排序方法，包括选择排序、插入排序和希尔排序。所有排序方法
 * 都为公有静态方法，可以直接使用类名调用
 * @ClassName: SortArray
 * @Description: SortArray是一个用于解决数组排序问题的类，包含有多种不同的数组排序方法 				 
 * @author 小尚同学
 * @date 2020年11月19日
 *
 */
public class SortArray {
	/*
	 * Java泛型语法：<T extends Comparable<T>>对数据类型T进行了限定，T必须为实现了Comparable接口的类
	 * 				Comparable<? super T>中的？是通配符，代表任意的数据类型，<? super T>表示T的任意父
	 * 				类,综合起来就是T必须为实现了数据类型为T的任意父类的Comparable接口的类
	 */
	/*
	 * 假设需要对数组a进行升序排序，a中包含n个元素。迭代选择排序的思想是对数组进行n次查找，
	 *第i次查找从数组第i个元素开始，找到值最小的元素，将其和第i个元素交换。但n次查找结束后，
	 *数组排序完成。 
	 */
	/**
	 * @Title: selectionSort
	 * @Description: 使用迭代选择排序算法实现的数组排序算法。
	 * @param <T> T必须为实现了数据类型为T的任意父类的Comparable接口的类
	 * @param a 待排序的数组
	 * @param n 数组包含的元素数量
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
	 * @Description: 使用递归选择排序算法对数组的指定片段进行升序排序
	 * @param <T>
	 * @param a 待排序的数组
	 * @param first 片段起始位置
	 * @param last 片段结束位置
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
	 * @Description:返回数组指定片段中的最小值的数组下标 
	 * @param <T>
	 * @param a 需要查找的数组
	 * @param first 查找起始位置
	 * @param last 查找结束位置
	 * @return 最小值的数组下标
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
	
	//交换数组元素a[i]和a[j]
	private static void swap(Object[] a,int i,int j) {
		Object temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	/**
	 * 插入排序将数组分隔为两个部分，第一个部分是有序的，初始时仅含有数组中的第一项。
	 * 第二个部分含有其余的项。算法从未排序部分移走第一项，并将它插入有序部分中合适
	 * 的有序位置。
	 * @Title: insertionSort
	 * @Description: 使用迭代插入排序算法对数组的指定片段进行升序排序
	 * @param <T>
	 * @param a 需要排序的数组
	 * @param first 片段开始位置
	 * @param last 片段结束位置
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] a,int first,int last) {
		for(int unsorted=first+1;unsorted<=last;unsorted++) {
			T nextToInsert=a[unsorted];
			insertInOrder(nextToInsert,a,first,unsorted-1);//将未排序部分的第一项插入到有序部分的合适位置
		}
	}
	
	/**
	 * @Title: insertInOrder
	 * @Description: 将anEntry插入到有序部分的合适位置
	 * @param <T>
	 * @param anEntry 是一个未完成排序的对象
	 * @param a 需要排序的数组
	 * @param begin 有序部分的起始位置
	 * @param end 有序部分的结束位置
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void insertInOrder(T anEntry,T[] a,int begin,int end) {
		int index=end;
		while((index>=begin)&&(anEntry.compareTo(a[index])<0)) {//从有序部分从后往前查找，找到第一个小于等于anEntry的值，这个值后面一个位置就是插入的位置
			a[index+1]=a[index];//为插入腾出位置
			index--;
		}
		a[index+1]=anEntry;//插入
		
	}
	
	/*
	 *  希尔排序是插入排序的变种。根据插入排序在对基本有序的序列进行排序时的时间复杂度接近于O(n)的特点，
	 * 希尔排序先将序列转化为基本有序的序列，最后再对数组进行一次普通的插入排序，完成排序。 其做法是，
	 * 先将序列分为space组，每一组是元素之间步长为space的子序列，对这些子序列序列进行插入排序，通过不
	 * 断缩小space的值，使得序列基本有序。当最后space为1时，希尔排序便变为了普通的插入排序。
	 * 实际上，只要最后space的值为1，希尔排序就能够起到排序作用。但并不是所有的space序列都能够使希尔排
	 * 序拥有高效率。希尔排序算法的作者Shell建议，space的初始值取数组长度的一半，之后space不断减半直到
	 * 为1。
	 */
	/**
	 * sheelSort是使用希尔排序算法思想的数组排序方法，可以对指定数组的子片段进行排序
	 * @Title: sheelSort
	 * @Description: 使用希尔排序算法对数组的子片段进行升序排序
	 * @param <T>
	 * @param a 待排序的数组
	 * @param first 子片段的起始位置
	 * @param last 子片段的结束位置
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
	 * @Description:希尔排序的子排序部分，对数组a中以first为首，步长为space的子序列进行插入排序 
	 * @param <T>
	 * @param a 需要排序的数组
	 * @param first 子序列开始元素的下标
	 * @param last 数组最后一个元素的下标
	 * @param space 子序列元素之间的步长
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void incrementalInsertionSort(T[] a,int first,int last,int space) {
		for(int unsorted=first+space;unsorted<=last;unsorted+=space) {//遍历访问数组中first为首，space为步长的子序列，忽略first，因为first已经有序
			T nextToInsert=a[unsorted];//未排序部分的第一个元素
			int index=unsorted-space;//有序部分的最后一个元素
			while((index>=first)&&(nextToInsert.compareTo(a[index])<0)) {//从后往前查找，找到第一个小于等于nextToInsert的元素，这个元素的后一个位置便是插入位置
				a[index+space]=a[index];
				index=index-space;//为插入腾出位置
			}
			a[index+space]=nextToInsert;//插入元素
		}
	}
	
	/*
	 * 归并排序运用了分治的思想，将数组分为两半，分别对两半进行排序，然后将这两段有序部分合并到另一个临时数组中。
	 * 通过递归调用归并方法对划分出的两个部分进行排序。
	 */
	/**
	 * mergeSort是使用归并排序算法解决排序问题的方法
	 * @Title: mergeSort
	 * @Description: 使用归并排序方法对一个数组片段进行排序
	 * @param <T>
	 * @param a 待排序片段所在的数组
	 * @param first 片段的起始位置
	 * @param last 片段的结束位置
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] a,int first,int last) {
		@SuppressWarnings("unchecked")
		T[] tempArray=(T[])new Comparable<?>[a.length];
		mergeSort(a,tempArray,first,last);
	}
	
	/**
	 * @Title: mergeSort
	 * @Description: 使用递归方式实现的归并排序，可以对一个数组片段进行升序排序
	 * @param <T>
	 * @param a 需要排序的数组
	 * @param tempArray 暂存数组
	 * @param first 片段的起始位置
	 * @param last 片段的结束位置
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
	 * @Description: 将数组中两个有序部分进行合并 
	 * @param <T>
	 * @param a 需要进行排序的数组
	 * @param tempArray 暂存数组
	 * @param first 第一个有序部分的起始位置
	 * @param mid 第一个有序部分的结束位置
	 * @param last 第二个有序部分的结束位置
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> void merge(T[] a,T[] tempArray,int first,int mid,int last) {
		int beginHalf1=first;
		int endHalf1=mid;
		int beginHalf2=mid+1;
		int endHalf2=last;
		int index=0;
		while((beginHalf1<=endHalf1)&&(beginHalf2<=endHalf2)) {//根据两个有序数组的首元素将其归并到临时数组
			if(a[beginHalf1].compareTo(a[beginHalf2])<=0) {
				tempArray[index]=a[beginHalf1];
				beginHalf1++;
			}else {
				tempArray[index]=a[beginHalf2];
				beginHalf2++;
			}
			index++;
		}
		//assert:一个子数组已经全部复制到tempArray中
		
		//将另一个子数组中剩余的项复制到tempArray中
		while(beginHalf1<=endHalf1) {
			tempArray[index++]=a[beginHalf1++];
		}
		while(beginHalf2<=endHalf2) {
			tempArray[index++]=a[beginHalf2++];
		}
		//assert:两个while语句只有一个会调用
		index=0;
		for(int i=first;i<=last;i++) {//将tempArray中的值复制回a数组中的对应片段
			a[i]=tempArray[index++];
		}
	}
	
	/**
	 * 快速排序将数组划分为两部分，在排序过程中，快速排序选择数组中的一项（俗称枢轴）来重排数组，满足：
	 * 				枢轴所处的位置就是在有序数组中的最终位置
	 * 				枢轴前的项都小于或等于枢轴
	 * 				枢轴后的项都大于或等于枢轴
	 * 这个排列称为数组的划分。创建划分将数组分为两个部分，称为较小部分和较大部分，它们由枢轴分开。继续
	 * 对较小部分和较大部分使用快速排序进行排序，最终原始数组将是有序的。
	 * @Title: quickSort
	 * @Description:用快速排序算法解决排序问题 
	 * @param <T>
	 * @param a 需要排序的数组
	 * @param first 数组片段的起始位置
	 * @param last 数组片段的结束位置
	 * @throws 
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] a,int first,int last) {
		if(last-first+1<4) {//在调用划分方法前，要求数组至少要有4项，以此，小于4项的数组使用插入排序
			insertionSort(a,first,last);
		}else {
			int pivotIndex=partition(a,first,last);//对数组进行划分，获取枢轴的下标
			quickSort(a,first,pivotIndex-1);//对较小部分进行排序
			quickSort(a,pivotIndex+1,last);//对较大部分进行排序
		}
	}
	
	/**
	 * 三元中值枢轴选择法，可以避免快速排序出现最坏时间复杂度。
	 * @Title: sortFirstMiddleLast
	 * @Description: 将数组的第一项、中间项及最后一项的下标传给这个方法后，枢轴将位于中间下标处。
	 * @param <T>
	 * @param a 待排序的数组
	 * @param first 待排序片段的第一项
	 * @param mid 待排序片段的中间项
	 * @param last 待排序片段的最后一项
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
	 * 划分的过程为：从数组头开始，向数组为前进，查找第一个大于或等于枢轴的项，下标为indexFromLeft。用
	 * 类似的方式，从倒数第二项开始向数组头前进，查找第一个小于或等于枢轴的项，下标为indexFromRight，如
	 * 果indexFromLeft小于indexFromRight，则交换这两个项。继续从左往右和从右往左进行查找，直到indexFro
	 * mLeft不小于indexFromRight。最后交换indexFromLeft与last。
	 * @Title: partition
	 * @Description:将数组片段的中间项作为枢轴，使枢轴前的项都小于或等于枢轴，枢轴后的项都大于或等于枢轴，返回枢轴元素最终所处的下标，从而对数组进行划分
	 * @param <T>
	 * @param a 需要排序的数组
	 * @param first 片段开始的位置
	 * @param last 片段结束的位置
	 * @return 返回枢轴最终所处的下标
	 * @throws 
	 */
	private static <T extends Comparable<? super T>> int partition(T[] a,int first,int last) {
		int mid=(first+last)/2;
		//对数组片段的第一项、中间项和最后一项进行排序，使得这三项的中位数处于中间项，以避免快速排序出现最坏时间复杂度
		sortFirstMiddleLast(a,first,mid,last);
		swap(a,mid,last-1);			
		int pivotIndex=last-1;//枢轴所在的位置			
		T pivotValue=a[pivotIndex];//枢轴的值
		int indexFromLeft=first+1;
		int indexFromRight=last-2;
		boolean done=false;
		while(!done) {
			while(a[indexFromLeft].compareTo(pivotValue)<0)//从左到右找到下一个大于等于枢轴的值的下标
				indexFromLeft++;
			while(a[indexFromRight].compareTo(pivotValue)>0)//从右到左找到下一个小于等于枢轴的值的下标
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
	 * @Description: 使用基数排序解决整数数组的排序问题
	 * @param a 待排序的整数数组
	 * @param first 数组片段的起始位置
	 * @param last 数组片段的结束位置
	 * @param maxDigiths 最长整数的数字位数
	 * @throws 
	 */
	public static void radixSort(int[] a,int first,int last) {
		@SuppressWarnings("unchecked")
		Deque<Integer>[] bucket =(ArrayDeque<Integer>[]) new Object[10];//根据数的进制创建桶  
		int maxDights=getMaxDights(a,first,last);//取得数组中最大值的位数
		for(int i=1;i<=maxDights;i++) {//从数的低位到高位对数组进行排序
			for(int j=0;j<10;j++) {//清空所有的桶
				bucket[j].clear();
			}
			for(int index=first;index<=last;index++) {//将第index位是i的数添加进第i个桶
				int dight=getDightAt(a[index],i);
				bucket[dight].add(a[index]);
			}
			for(int j=0;j<10;j++) {//将10个桶中的元素按照0..9的顺序添加回原来的数组
				int index=0;
				while(!bucket[j].isEmpty()) {
					a[index++]=bucket[j].poll();
				}
			}
		}
	}
	
	/**
	 * @Title: getMaxDights
	 * @Description:返回数组片段中最大值的位数 
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
	 * @Description:从低位向高位数，获取一个整数的第index位的值，如果不存在该位，返回0 
	 * @param num 是一个整数
	 * @param index 需要获取的位
	 * @return 整数第index位的值，如果不存在该位，返回0
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
