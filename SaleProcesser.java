
public class SaleProcesser {
	MessageProcesser processer;
	Product product;
	ProductInventoryLogger productList;
	String line;

	public SaleProcesser() {
		productList=new ProductInventoryLogger();
	}
	

	public void processSaleNotifications(String line) {
		processer = new MessageProcesser(line);
		processer.parseMessage(line);
		String productType = processer.getProductType();
		if (productType != null)
			this.product = getProduct(productType);

		// Set the product details from the parsed message
		this.product.setProductQuantity(processer.getProductQuantity());
		this.product.setTotalQuantity(processer.getProductQuantity());
		this.product.setProductPrice(processer.getProductPrice());
		this.product.setAdjustmentOperator(processer.getOperatorType());

		// Set the total value of the product.
		setProductTotalPrice();
		ProductInventoryLogger.report(line);
		ProductInventoryLogger.updateProduct(product);

	}

	private void setProductTotalPrice() {
		int adjustedPrice;
		int productValue;

		if (!product.getAdjustmentOperator().isEmpty()) {
			adjustedPrice = CalculateCorrectPrice.getCorrectedPrice();
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
			product.appendTotalPrice(productValue);
		}
	}


	private Product getProduct(String productType) {
		
		
		Product prod = productList.getProduct(productType);
		System.out.println("SaleProcesser.getProduct(prod)"+prod);
		return prod;
	}

}
