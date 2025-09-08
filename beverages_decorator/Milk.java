package beverages_decorator;

public class Milk extends CondimentDecorator {

	private final Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	@Override
	public int cost() {
		return beverage.cost() + 3;
	}
}


