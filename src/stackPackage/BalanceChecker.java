package stackPackage;


/**
 * BanlanceChecker是一个用于检查中缀代数表达式中的分隔符是否平衡的类，分隔符
 * 是指小括号'('和')'，中括号'['和']'以及大括号'{'和'}'。我们将'('、'['、'{'称为左括号，将
 * 与之匹配的另一半称为右括号。分隔符不平衡是指以下三种情况或他们的复合情况，
 * 1、右括号与它左侧最近的左括号不匹配，如"[)"
 * 2、存在一个左括号没有右括号与他匹配，如"[(){}"
 * 3、存在一个右括号没有左括号与他匹配，如"(){}]"
 * @ClassName: BalanceChecker
 * @Description: 用于检查中缀代表表达式中的分隔符是否平衡
 * @author 小尚同学
 * @date 2020年11月15日
 *
 */
public class BalanceChecker {
	public static void main(String[] args) {
		String[] expression= {	"[a{b/(c-d)+e/(f+g)}-h]",
								"{a[b+(c+2)/d]+e)+f}",
								"[a{b+[c(d+e)-f]+g}"
							};
		for(int i=0;i<expression.length;i++) {
			System.out.println("表达式："+expression[i]);
			System.out.println("分隔符平衡："+(checkBalance(expression[i])?"YES":"NO"));
		}	
	}
	
	/*
	 * 算法思路的主要思路是检查中缀代数表达式中是否存在导致分割符不平衡的三种情况，
	 * 考虑到检查括号是否匹配时，需要将右括号与最近一个未检查的左括号进行匹配，故
	 * 算法使用了栈结构来存储未检查的左括号。算法思想如下，每遇见一个左括号，将其
	 * 压入栈内。每遇见一个右括号，将其与栈顶的左括号进行匹配，如果匹配成功，将左
	 * 括号弹出，否则出现分隔符不平衡的第一种情况。若遇见右括号时，栈为空，说明出
	 * 现分隔符不平衡的第三种情况。当中缀代数表达式已经检查完毕后，若栈仍然非空，
	 *说明出现分隔符不平衡的第二种情况。若三种情况都未出现，说明分隔符平衡。
	 */
	/**
	 * @Title: checkBalance
	 * @Description: 检查中缀表达式中的分隔符是否平衡，仅检查'()'、'[]'和'{}'三对分隔符，其他分割符将被忽略
	 * @param expression 待检查的中缀代数表达式
	 * @return boolean 根据分隔符是否平衡返回真或假
	 * @throws 
	 */
	public static boolean checkBalance(String expression) {
		StackInterface<Character> openDelimiterStack=new LinkedStack<>();//创建一个空栈，用来存储未检左括号
		int characterCount=expression.length();
		boolean isBalanced=true;//没有要配对的分界符
		int index=0;
		char nextCharacter=' ';
		while(isBalanced&&(index<characterCount)) {//判断中缀算术表达式是否检查完毕
			nextCharacter=expression.charAt(index);
			switch(nextCharacter) {
				case '(':case '{':case '[':
					openDelimiterStack.push(nextCharacter);
					break;
				case ')':case '}':case ']':
					if(openDelimiterStack.isEmpty())//检查是否存在有右括号没有左括号与它匹配
						isBalanced=false;
					else {
						char openDelimiter=openDelimiterStack.pop();
						isBalanced=isPired(openDelimiter,nextCharacter);//检查是否存在有右括号和与它最近的未检左括号不匹配
					}
					break;
				default:break;
			}
			index++;
		}
		if(!openDelimiterStack.isEmpty())//检查是否存在有左括号没有右括号与它匹配
			isBalanced=false;
		return isBalanced;
	}
	
	/**
	 * @Title: isPired
	 * @Description:判断左括号与右括号是否匹配 
	 * @param open 左括号
	 * @param close 右括号
	 * @return 根据左括号与右括号是否匹配返回真或假
	 * @throws 
	 */
	private static boolean isPired(char open,char close) {
		return  (open=='[')&&(close==']')||
				(open=='{')&&(close=='}')||
				(open=='(')&&(close==')');
	}
}
