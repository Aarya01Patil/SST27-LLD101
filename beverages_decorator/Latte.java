package beverages_decorator;

public class Latte extends Beverage {

	public Latte() {
		this.description = "Latte";
	}

	@Override
	public int cost() {
		return 20;
	}

}