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
		// TODO 在栈顶添加一个元素
		this.add(size(), item);
		return item;
	}

	@Override
	public AnyType pop() {
		// TODO 弹出栈顶元素
		AnyType obj=peek();
		remove(size()-1);
		return obj;
	}

	@Override
	public AnyType peek() {
		// TODO 返回栈顶元素
		if(isEmpty())throw new EmptyStackException();
		return this.get(size()-1);
	}

	@Override
	public int search(AnyType item) {
		// TODO 从栈顶开始查找，返回item是第几个元素，若未找到，返回-1
		int idx=lastIndexOf(item);
		if(idx>=0)return size()-idx;
		return -1;
	}
	
}
