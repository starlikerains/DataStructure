package bagPackage;

public class PiggyBankExample {
	public static void main(String[] args) {
		PiggyBank myBank=new PiggyBank();
		addCoin(new Coin(1,2010),myBank);
		addCoin(new Coin(5,2011),myBank);
		addCoin(new Coin(10,2000),myBank);
		addCoin(new Coin(25,2012),myBank);
		
		System.out.println("Removing all the coins:");
		int amountRemoved=0;
		
		while(!myBank.isEmpty()) {
			Coin removedCoin=myBank.remove();
			System.out.println("Removed a "+removedCoin.getYear()+".");
			amountRemoved=amountRemoved+removedCoin.getValue();
		}
		System.out.print("All done.Removed "+amountRemoved+" cents.");
	}
	public static void addCoin(Coin aCoin,PiggyBank aBank) {
		if(aBank.add(aCoin))
			System.out.println("Added a "+aCoin.getYear()+".");
		else
			System.out.println("Tried to add a "+aCoin.getYear()+",but couldn't");
	}
}
