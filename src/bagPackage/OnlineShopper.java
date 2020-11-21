package bagPackage;

/**
 * @ClassName: OnlineShopper
 * @Description:一个维护在线购物购物袋的类 
 * @author 小尚同学
 * @date 2020年11月13日
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
		
		//模拟向购物袋中添加商品
		for(int index=0;index<items.length;index++) {
			Item nextItem=items[index];
			shoppingCart.add(nextItem);
			totalCost=totalCost+nextItem.getPrice();
		}
		
		//模拟检测
		while(!shoppingCart.isEmpty())
			System.out.println(shoppingCart.remove());
		
		System.out.println("总支出:"+"\t$"+totalCost/100+"."+totalCost%100);
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
