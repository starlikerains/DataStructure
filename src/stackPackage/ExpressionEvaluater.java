package stackPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * ConvertToPostfixer���ڽ���׺�������ʽת��Ϊ��׺�������ʽ����׺�������ʽ��ָ�����
 * ���������������м䣬����"a*b+c",��׺���ʽ��ָ��������������������󷽣�����"ab*+",
 * "a*b+c"��"ab*+"�ȼۡ�ConvertToPostfixer������������������(+)����(-)����(*)����(/)�Լ�
 * ��(^)���֣���������֮��������ȼ���ϵ,"()"���ȼ���ߣ���()�еı��ʽ���ȱ����㣬�������
 * ���㣬Ȼ���ǳ˳����㣬����ǼӼ����㡣��׺���ʽ��������������ȼ��Ա��ʽ�������㣬����
 * �����ȼ��ߵ��Ƚ������㣬���ȼ���ͬ�ı��ʽ�����������㡣
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��15��
 *
 */
/**
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��15��
 *
 */
/**
 * @ClassName: ConvertToPostfixer
 * @Description: 
 * @author С��ͬѧ
 * @date 2020��11��15��
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
			System.out.println("��׺���ʽ��"+infix[index]+",Ԥ�ڽ����"+expectedPostfix[index]);
			String result=convertToPostfix(infix[index]);
			System.out.println("ת�������"+result);
			System.out.println("�ɹ�ת����"+(expectedPostfix[index].equals(result)?"YES":"NO"));
		}	
	}
	
	private static void testEvaluatePostfix(String[] postfixs,Map<Character,Double> map) {
		System.out.println("������ֵ"+map);
		for(int index=0;index<postfixs.length;index++) {
			System.out.println("������ֵ"+map);
			System.out.println("��׺���ʽ��"+postfixs[index]);
			System.out.println("��������"+evaluatePostfix(postfixs[index],map));
		}
	}
	
	private static void testEvaluateInfix(String[] infixs,Map<Character,Double> map) {
		System.out.println("������ֵ"+map);
		for(int index=0;index<infixs.length;index++) {
			System.out.println("������ֵ"+map);
			System.out.println("��׺���ʽ��"+infixs[index]);
			System.out.println("��������"+evaluateInfix(infixs[index],map));
		}
	}
	/*
	 * 
	 */
	/**
	 * @Title: convertToPostfix
	 * @Description: ����׺�������ʽת��Ϊ��׺�������ʽ
	 * @param infix ��Ҫ����ת������׺�������ʽ�����������׺���ʽ�����ȷ����ÿ������������һ����ĸ��ʾ
	 * @return String ת���õĺ�׺���ʽ
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
					//��������������ֱ�ӽ������ѹ��ջ��
					case '^':
						operatorStack.push(nextCharacter);
						break;
					//����ǼӼ��˳�����������ϵ������ȼ�С�ڵ���nextCharacter���������ֱ��ջ�ջ���������
					//�����ߵ����������nextCharacterѹ��ջ��
					case '+':case '-':case '*':case '/':
						while(!operatorStack.isEmpty()&&isNotHighPriority(nextCharacter,operatorStack)) {
							postfix.append(operatorStack.peek());
							operatorStack.pop();
						}
						operatorStack.push(nextCharacter);
						break;
					//����������ţ�ֱ�ӽ�������ѹ��ջ��
					case '(':
						operatorStack.push(nextCharacter);
						break;
					//����������ţ����ϵ����������ֱ�������ű�����
					case ')':
						assert operatorStack.isEmpty()!=true;
						char topOperator=operatorStack.pop();					
						while(topOperator!='(') {
							postfix.append(topOperator);
							topOperator=operatorStack.pop();
						}
						break;
					//������������ţ�����
					default:break;
				}
			index++;
		}
		//��ʣ������������
		while(!operatorStack.isEmpty()) {
			char topOperator=operatorStack.pop();
			postfix.append(topOperator);
		}
		return postfix.toString();
	}
	
	/**
	 * @Title: evaluatePostfix
	 * @Description: �����׺���ʽ��ֵ
	 * @param postfix ������ĺ��ñ��ʽ
	 * @param map �洢������ֵ��ֵ�Ե�map����
	 * @return ������
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
	 * @Description:�жϵ�ǰ�ַ��Ƿ�Ϊ���� 
	 * @param aChar ��Ҫ�жϵ��ַ�
	 * @return boolean ����ַ�����ĸ������true�����򷵻�false
	 * @throws 
	 */
	private static boolean isVariable(char aChar) {
		return 	'a'<=aChar&&aChar<='z'||
				'A'<=aChar&&aChar<='Z';
	}
	
	
	/**
	 * @Title: isNotHighPriority
	 * @Description:�жϵ�ǰ����������ȼ��Ƿ�С�ڵ���ջ������� 
	 * @param operator ��Ҫ�жϵ������
	 * @param stack �洢�������ջ
	 * @return �����������ȼ�С�ڵ���ջ�������������true�����򷵻�false
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
