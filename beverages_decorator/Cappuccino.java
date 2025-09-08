package beverages_decorator;

public class Cappuccino extends Beverage{

	public Cappuccino() {
		this.description = "Cappuccino";
	}

	@Override
	public int cost() {
		return 10;
	}

}