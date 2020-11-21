package bagPackage;

/**
 * @ClassName: OnlineShopper
 * @Description:һ��ά�����߹��ﹺ������� 
 * @author С��ͬѧ
 * @date 2020��11��13��
 *
 */
public class OnlineShopper {
	public static void main(String[] args) {
		Item[] items= {
				new Item("Bird feeder",2050),
				new Item("Squirrel guard",1547),
				new Item("Sunflower seeds",1295)
		};
		BagInterface<Item> shoppingCart=new ArrayBag<>();
		int totalCost=0;
		
		//ģ��������������Ʒ
		for(int index=0;index<items.length;index++) {
			Item nextItem=items[index];
			shoppingCart.add(nextItem);
			totalCost=totalCost+nextItem.getPrice();
		}
		
		//ģ����
		while(!shoppingCart.isEmpty())
			System.out.println(shoppingCart.remove());
		
		System.out.println("��֧��:"+"\t$"+totalCost/100+"."+totalCost%100);
	}
}

class Item{
	private String itemName;
	private int itemPrice;
	public Item(String aName,int aPrice) {
		itemName=aName;
		itemPrice=aPrice;
	}
	public String getName() {
		return itemName;
	}
	public int getPrice() {
		return itemPrice;
	}
	public String toString() {
		return itemName+"\t$"+itemPrice/100+"."+itemPrice%100;
	}
}
