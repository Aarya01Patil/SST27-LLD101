package beverages_decorator;

public abstract class Beverage {
	protected String description = "Unknown Beverage";

	public String getDescription() {
		return description;
	}

	public abstract int cost();

}