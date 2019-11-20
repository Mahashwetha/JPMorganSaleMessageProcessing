
public class CalculateCorrectPrice {

	private static int adjustedPrice;

	// product holds the Product object.
	private static Product product;

	public CalculateCorrectPrice(Product product) {
		this.product = product;
		this.adjustedPrice = 0;
	}

	public static int getCorrectedPrice() {
		String op = product.getAdjustmentOperator();
		if(op=="Add")
			addPrice();
		else if(op=="Subtract")
			subtractPrice();
		else if(op=="Multiply")
			multiplyPrice();
		return adjustedPrice;
	}

	//  Adds product totalprice with the requested price value.
		public static void addPrice() {
			adjustedPrice = product.getTotalPrice()
					+ (product.getTotalQuantity() * product.getProductPrice());
		}

		// Subtracts product totalprice with the requested price
		// value.
		public static void subtractPrice() {
			adjustedPrice = product.getTotalPrice()
					- (product.getTotalQuantity() * product.getProductPrice());
		}

		//  Multiplies product total price and quantity with the
		// requested price and appends to existing total value.
		public static void multiplyPrice() {
			adjustedPrice = product.getTotalPrice()
					+ (product.getTotalPrice() * product.getProductPrice())
					+ (product.getTotalQuantity() * product.getProductPrice());
		}

}
