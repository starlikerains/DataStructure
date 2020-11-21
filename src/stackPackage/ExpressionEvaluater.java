package stackPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * ConvertToPostfixer用于将中缀算术表达式转化为后缀算术表达式。中缀算术表达式是指运算符
 * 处于两个操作数中间，例如"a*b+c",后缀表达式是指运算符处于两个操作数后方，例如"ab*+",
 * "a*b+c"与"ab*+"等价。ConvertToPostfixer处理的运算符仅包括加(+)、减(-)、乘(*)、除(/)以及
 * 幂(^)五种，五个运算符之间存在优先级关系,"()"优先级最高，在()中的表达式最先被运算，其次是幂
 * 运算，然后是乘除运算，最后是加减运算。中缀表达式根据运算符的优先级对表达式进行运算，规则
 * 是优先级高的先进行运算，优先级相同的表达式从左向右运算。
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 */
/**
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 */
/**
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author 小尚同学
 * @date 2020年11月15日
 *
 */
public class ExpressionEvaluater {
	public static void main(String[] args) {
		String[] infix= {
				"a+b",
				"(a+b)*c",
				"a+b*c",
				"a*b/(c-d)",
				"a/b+(c-d)",
				"a/b+c-d"
		};
		String[] postfix= {
				"ab+",
				"ab+c*",
				"abc*+",
				"ab*cd-/",
				"ab/cd-+",
				"ab/c+d-"
		};
		Map<Character,Double> map=new HashMap<>();
		map.put('a',20.0);
		map.put('b',5.0);
		map.put('c',2.0);
		map.put('d',3.0);
		testConvertToPostfix(infix,postfix);
		testEvaluatePostfix(postfix,map);
		testEvaluateInfix(infix,map);
	}
	
	private static void testConvertToPostfix(String[] infix,String[] expectedPostfix) {
		for(int index=0;index<infix.length;index++) {
			System.out.println("中缀表达式："+infix[index]+",预期结果："+expectedPostfix[index]);
			String result=convertToPostfix(infix[index]);
			System.out.println("转换结果："+result);
			System.out.println("成功转换："+(expectedPostfix[index].equals(result)?"YES":"NO"));
		}	
	}
	
	private static void testEvaluatePostfix(String[] postfixs,Map<Character,Double> map) {
		System.out.println("变量与值"+map);
		for(int index=0;index<postfixs.length;index++) {
			System.out.println("变量与值"+map);
			System.out.println("后缀表达式："+postfixs[index]);
			System.out.println("计算结果："+evaluatePostfix(postfixs[index],map));
		}
	}
	
	private static void testEvaluateInfix(String[] infixs,Map<Character,Double> map) {
		System.out.println("变量与值"+map);
		for(int index=0;index<infixs.length;index++) {
			System.out.println("变量与值"+map);
			System.out.println("中缀表达式："+infixs[index]);
			System.out.println("计算结果："+evaluateInfix(infixs[index],map));
		}
	}
	/*
	 * 
	 */
	/**
	 * @Title: convertToPostfix
	 * @Description: 将中缀算术表达式转化为后缀算术表达式
	 * @param infix 需要进行转化的中缀代数表达式，这里假设中缀表达式表达正确，且每个操作数仅用一个字母表示
	 * @return String 转化好的后缀表达式
	 * @throws 
	 */
	public static String convertToPostfix(String infix) {
		StackInterface<Character> operatorStack=new LinkedStack<>();
		StringBuilder postfix=new StringBuilder();
		int characterCount=infix.length();
		int index=0;
		char nextCharacter=' ';
		while(index<characterCount) {
			nextCharacter=infix.charAt(index);
			if(isVariable(nextCharacter))
				postfix.append(nextCharacter);
			else
				switch(nextCharacter) {
					//如果是幂运算符，直接将运算符压入栈中
					case '^':
						operatorStack.push(nextCharacter);
						break;
					//如果是加减乘除运算符，不断弹出优先级小于等于nextCharacter的运算符，直到栈空或遇见优先
					//级更高的运算符，将nextCharacter压入栈中
					case '+':case '-':case '*':case '/':
						while(!operatorStack.isEmpty()&&isNotHighPriority(nextCharacter,operatorStack)) {
							postfix.append(operatorStack.peek());
							operatorStack.pop();
						}
						operatorStack.push(nextCharacter);
						break;
					//如果是左括号，直接将左括号压入栈中
					case '(':
						operatorStack.push(nextCharacter);
						break;
					//如果是右括号，不断弹出运算符，直到左括号被弹出
					case ')':
						assert operatorStack.isEmpty()!=true;
						char topOperator=operatorStack.pop();					
						while(topOperator!='(') {
							postfix.append(topOperator);
							topOperator=operatorStack.pop();
						}
						break;
					//如果是其他符号，忽略
					default:break;
				}
			index++;
		}
		//将剩余的运算符弹出
		while(!operatorStack.isEmpty()) {
			char topOperator=operatorStack.pop();
			postfix.append(topOperator);
		}
		return postfix.toString();
	}
	
	/**
	 * @Title: evaluatePostfix
	 * @Description: 计算后缀表达式的值
	 * @param postfix 待计算的后置表达式
	 * @param map 存储变量与值键值对的map对象
	 * @return 计算结果
	 * @throws 
	 */
	public static double evaluatePostfix(String postfix,Map<Character,Double> map) {
		StackInterface<Double> valueStack=new LinkedStack<>();
		int index=0;
		int characterCount=postfix.length();
		char nextCharacter=' ';
		while(index<characterCount) {
			nextCharacter=postfix.charAt(index);
			if(isVariable(nextCharacter))
				valueStack.push(map.get(nextCharacter));
			else				
				switch(nextCharacter) {
					case '+':case '-':case '*':case '/':case '^':
						double operandTwo=valueStack.pop();
						double operandOne=valueStack.pop();
						double result=binaryCalculate(operandOne,operandTwo,nextCharacter);
						valueStack.push(result);
						break;
					default:break;
				}
			index++;
		}
		return valueStack.peek();
	}
	
	public static double evaluateInfix(String infix,Map<Character,Double> map) {
		String postfix=convertToPostfix(infix);
		return evaluatePostfix(postfix,map);
	}
	
	private static double binaryCalculate(double operandOne,double operandTwo,char operator) {
		double result=0;
		switch(operator) {
			case '+':
				result=operandOne+operandTwo;
				break;
			case '-':
				result=operandOne-operandTwo;
				break;
			case '*':
				result=operandOne*operandTwo;
				break;
			case '/':
				result=operandOne/operandTwo;
				break;
			case '^':
				result=Math.pow(operandOne, operandTwo);
			default:break;
		}
		return result;
	}
	
	
	
	
	/**
	 * @Title: isVariable
	 * @Description:判断当前字符是否为变量 
	 * @param aChar 需要判断的字符
	 * @return boolean 如果字符是字母，返回true，否则返回false
	 * @throws 
	 */
	private static boolean isVariable(char aChar) {
		return 	'a'<=aChar&&aChar<='z'||
				'A'<=aChar&&aChar<='Z';
	}
	
	
	/**
	 * @Title: isNotHighPriority
	 * @Description:判断当前运算符的优先级是否小于等于栈顶运算符 
	 * @param operator 需要判断的运算符
	 * @param stack 存储运算符的栈
	 * @return 如果运算符优先级小于等于栈顶运算符，返回true，否则返回false
	 * @throws 
	 */
	private static boolean isNotHighPriority(char operator,StackInterface<Character> stack) {
		boolean result=false;
		char topOperator=stack.peek();
		if((topOperator=='(')) {
			result=false;
		}else if((operator=='-'||operator=='+')) {
			result=true;
		}else {
			if(topOperator=='*'||topOperator=='/'||topOperator=='^')
				result=true;
		}
		return result;
	}
}
