package stackPackage;


/**
 * BanlanceChecker��һ�����ڼ����׺�������ʽ�еķָ����Ƿ�ƽ����࣬�ָ���
 * ��ָС����'('��')'��������'['��']'�Լ�������'{'��'}'�����ǽ�'('��'['��'{'��Ϊ�����ţ���
 * ��֮ƥ�����һ���Ϊ�����š��ָ�����ƽ����ָ����������������ǵĸ��������
 * 1�������������������������Ų�ƥ�䣬��"[)"
 * 2������һ��������û������������ƥ�䣬��"[(){}"
 * 3������һ��������û������������ƥ�䣬��"(){}]"
 * @ClassName: BalanceChecker
 * @Description: ���ڼ����׺������ʽ�еķָ����Ƿ�ƽ��
 * @author С��ͬѧ
 * @date 2020��11��15��
 *
 */
public class BalanceChecker {
	public static void main(String[] args) {
		String[] expression= {	"[a{b/(c-d)+e/(f+g)}-h]",
								"{a[b+(c+2)/d]+e)+f}",
								"[a{b+[c(d+e)-f]+g}"
							};
		for(int i=0;i<expression.length;i++) {
			System.out.println("���ʽ��"+expression[i]);
			System.out.println("�ָ���ƽ�⣺"+(checkBalance(expression[i])?"YES":"NO"));
		}	
	}
	
	/*
	 * �㷨˼·����Ҫ˼·�Ǽ����׺�������ʽ���Ƿ���ڵ��·ָ����ƽ������������
	 * ���ǵ���������Ƿ�ƥ��ʱ����Ҫ�������������һ��δ���������Ž���ƥ�䣬��
	 * �㷨ʹ����ջ�ṹ���洢δ���������š��㷨˼�����£�ÿ����һ�������ţ�����
	 * ѹ��ջ�ڡ�ÿ����һ�������ţ�������ջ���������Ž���ƥ�䣬���ƥ��ɹ�������
	 * ���ŵ�����������ַָ�����ƽ��ĵ�һ�������������������ʱ��ջΪ�գ�˵����
	 * �ַָ�����ƽ��ĵ��������������׺�������ʽ�Ѿ������Ϻ���ջ��Ȼ�ǿգ�
	 *˵�����ַָ�����ƽ��ĵڶ�������������������δ���֣�˵���ָ���ƽ�⡣
	 */
	/**
	 * @Title: checkBalance
	 * @Description: �����׺���ʽ�еķָ����Ƿ�ƽ�⣬�����'()'��'[]'��'{}'���Էָ����������ָ����������
	 * @param expression ��������׺�������ʽ
	 * @return boolean ���ݷָ����Ƿ�ƽ�ⷵ������
	 * @throws 
	 */
	public static boolean checkBalance(String expression) {
		StackInterface<Character> openDelimiterStack=new LinkedStack<>();//����һ����ջ�������洢δ��������
		int characterCount=expression.length();
		boolean isBalanced=true;//û��Ҫ��Եķֽ��
		int index=0;
		char nextCharacter=' ';
		while(isBalanced&&(index<characterCount)) {//�ж���׺�������ʽ�Ƿ������
			nextCharacter=expression.charAt(index);
			switch(nextCharacter) {
				case '(':case '{':case '[':
					openDelimiterStack.push(nextCharacter);
					break;
				case ')':case '}':case ']':
					if(openDelimiterStack.isEmpty())//����Ƿ������������û������������ƥ��
						isBalanced=false;
					else {
						char openDelimiter=openDelimiterStack.pop();
						isBalanced=isPired(openDelimiter,nextCharacter);//����Ƿ�����������ź����������δ�������Ų�ƥ��
					}
					break;
				default:break;
			}
			index++;
		}
		if(!openDelimiterStack.isEmpty())//����Ƿ������������û������������ƥ��
			isBalanced=false;
		return isBalanced;
	}
	
	/**
	 * @Title: isPired
	 * @Description:�ж����������������Ƿ�ƥ�� 
	 * @param open ������
	 * @param close ������
	 * @return �������������������Ƿ�ƥ�䷵������
	 * @throws 
	 */
	private static boolean isPired(char open,char close) {
		return  (open=='[')&&(close==']')||
				(open=='{')&&(close=='}')||
				(open=='(')&&(close==')');
	}
}
