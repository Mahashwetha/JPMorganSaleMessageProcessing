
public class MessageProcesser {
	int productQuantity;
	String productType;
	String operation;
	int productValue;

	MessageProcesser(String message) {
		this.productQuantity = 0;
		this.productType = "";
		this.operation = "";
		this.productValue = 0;
		parseMessage(message);

	}

	public void parseMessage(String message) {

		if (message == null)
			System.out.println("No message received please retry");
		String[] parseMessage = message.trim().split(" ");
		String first = parseMessage[0];
		if (parseMessage[1].contentEquals("at")) {
			parseMessageType1(parseMessage);
		} else if (parseMessage[0].matches("^\\d+")) {
			parseMessageType2(parseMessage);
		} else if (first.matches("Add|Subtract|Multiply"))

		{
			parseMessageType3(parseMessage);
		} else
			System.out.println("Sorry ,wrong message type as input.Try again");

	}

	private void parseMessageType1(String[] parseMessage) {
		if (parseMessage.length > 3 || parseMessage.length < 3)
			System.out.println("Your input is wrong");
		this.productType = parseMessage[0];
		this.productQuantity = 1;
		this.productValue = Integer.parseInt(parseMessage[2].replaceAll("p", ""));

	}

	private void parseMessageType2(String[] parseMessage) {
		this.productType = parseType(parseMessage[3]);
		this.productValue = parsePrice(parseMessage[5]);
		this.productQuantity = Integer.parseInt(parseMessage[1]);
	}

	private void parseMessageType3(String[] parseMessage) {

		if (parseMessage.length > 3 || parseMessage.length < 3)
			System.out.println("Error in input please try again");
		this.operation = parseMessage[0];
		this.productType = parseType(parseMessage[2]);
		this.productQuantity = 0;
		this.productValue = parsePrice(parseMessage[1]);

	}

	public String parseType(String rawType) {
		String parsedType = "";
		String skipLastChar = rawType.substring(0, rawType.length() - 1);

		if (rawType.endsWith("o")) {
			parsedType = String.format("%soes", skipLastChar);
		} else if (rawType.endsWith("y")) {
			parsedType = String.format("%sies", skipLastChar);
		} else if (rawType.endsWith("h")) {
			parsedType = String.format("%shes", skipLastChar);
		} else if (!rawType.endsWith("s")) {
			parsedType = String.format("%ss", rawType);
		} else {
			parsedType = String.format("%s", rawType);
		}
		return parsedType.toLowerCase();
	}

	// Parse the price and get only value (eg:10p remove p)

	public int parsePrice(String parsePrice) {
		int price;
		price = Integer.parseInt(parsePrice.replaceAll("p", ""));
		return price;
	}

	// Get the product type
	public String getProductType() {
		return productType;
	}

	// Get the product price
	public int getProductPrice() {
		return productValue;
	}

	// Get the operator type
	public String getOperatorType() {
		return operation;
	}

	// Get the product quantity
	public int getProductQuantity() {
		return productQuantity;
	}

	

}
