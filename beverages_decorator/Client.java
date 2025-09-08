package beverages_decorator;

public class Client {

	public static void main(String[] args) {
		Beverage order1 = new Cappuccino();
		order1 = new Milk(order1);
		order1 = new Mocha(order1);
		order1 = new Whip(order1);

		System.out.println("Receipt 1");
		System.out.println(order1.getDescription());
		System.out.println("Total: Rs. " + order1.cost());

		Beverage order2 = new Latte();
		order2 = new Mocha(order2);
		order2 = new Mocha(order2);
		System.out.println("\nReceipt 2");
		System.out.println(order2.getDescription());
		System.out.println("Total: Rs. " + order2.cost());

	}

}