import java.util.ArrayList;
import java.util.HashMap;

public class ProductInventoryLogger {
	static HashMap<String, Product> prodInv;
	private static ArrayList simpleReport;
	ProductInventoryLogger()
	{
		prodInv=new HashMap<String, Product>();
	}
	public Product getProduct(String productType) {
		if(prodInv.get(productType)==null)
			return prodInv.put(productType, new Product(productType));
		else
		return prodInv.get(productType);
	}

	public static void updateProduct(Product product) {
		prodInv.put(product.getProductType(), product);
	}


	private int totalSalesValue;

	
	public void report() {

		// Report after 10th iteration and not at the beginning.
		if ((simpleReport.size() % 10) == 0 && simpleReport.size() != 0) {
		
			System.out.println("10 sales appended to log");
			System.out.println("*************** Log Report *****************");
			System.out.println("|SalesMessageProcessor.Product           |Quantity   |Value      |");
			prodInv.forEach((k, v) -> formatReports(k, v));
			System.out.println("-------------------------------------------");
			System.out.println(String.format("|%-30s|%-11.2f|", "Total Sales", getTotalSalesValue()));
			System.out.println("-------------------------------------------");
			System.out.println("End\n\n");
			try {
				// Add 2 second pause
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
		// Report and stop execution after 50th message
		if ((simpleReport.size() % 50) == 0 && simpleReport.size() != 0) {
			System.out.println(
					"Application reached 50 messages and cannot process further. The following are the adjustment records made;\n");

			
			System.exit(1);
		}
	}
	
	// Append any given value to the totalSalesValue field
			public void appendTotalSalesValue(int productTotalPrice) {
				totalSalesValue += productTotalPrice;
			}
			
	public void formatReports(String type, Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(),
				product.getTotalPrice());
		appendTotalSalesValue(product.getTotalPrice());
		System.out.println(lineItem);
	}

	
	public static void report(String line) {
	
		simpleReport.add(line);
	}

	// return the total sales value
		public double getTotalSalesValue() {
			return totalSalesValue;
		}
}
