package list;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MySequenceStack<AnyType> extends MyArrayList<AnyType> implements MyStack<AnyType>{
	public MySequenceStack() {
	}
	@Override
	public AnyType push(AnyType item) {
		// TODO ��ջ�����һ��Ԫ��
		this.add(size(), item);
		return item;
	}

	@Override
	public AnyType pop() {
		// TODO ����ջ��Ԫ��
		AnyType obj=peek();
		remove(size()-1);
		return obj;
	}

	@Override
	public AnyType peek() {
		// TODO ����ջ��Ԫ��
		if(isEmpty())throw new EmptyStackException();
		return this.get(size()-1);
	}

	@Override
	public int search(AnyType item) {
		// TODO ��ջ����ʼ���ң�����item�ǵڼ���Ԫ�أ���δ�ҵ�������-1
		int idx=lastIndexOf(item);
		if(idx>=0)return size()-idx;
		return -1;
	}
	
}
