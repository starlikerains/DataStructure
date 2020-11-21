package bagPackage;

public class PiggyBank {
	private BagInterface<Coin> coins;
	public PiggyBank() {
		coins=new ArrayBag<>();
	}
	public boolean add(Coin aCoin) {
		return coins.add(aCoin);
	}
	public Coin remove() {
		return coins.remove();
	}
	public boolean isEmpty() {
		return coins.isEmpty();
	}
}

class Coin{
	private int value;
	private int year;
	public Coin(int aValue,int aYear) {
		value=aValue;
		year=aYear;
	}
	public int getValue() {
		return value;
	}
	public int getYear() {
		return year;
	}
	public String toString() {
		return value+"бщ\t"+year;
	}
}