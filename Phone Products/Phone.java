public class Phone extends Product{

	private String phoneCreator;
	private String phoneBrand;
	
	public Phone() {
		
	}

	public Phone(int productID, String productName, double productPrice, int productQty, String productType,
			String phoneCreator, String phoneBrand) {
		super(productID, productName, productPrice, productQty, productType);
		this.phoneCreator = phoneCreator;
		this.phoneBrand = phoneBrand;
	}

	public String getPhoneCreator() {
		return phoneCreator;
	}

	public void setPhoneCreator(String phoneCreator) {
		this.phoneCreator = phoneCreator;
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}	
}
