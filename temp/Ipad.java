public class Ipad extends Product{

	private String ipadCreator;
	private String ipadBrand;
	
	public Ipad() {
		
	}

	public Ipad(int productID, String productName, double productPrice, int productQty, String productType,
			String ipadCreator, String ipadBrand) {
		super(productID, productName, productPrice, productQty, productType);
		this.ipadCreator = ipadCreator;
		this.ipadBrand = ipadBrand;
	}

	public String getIpadCreator() {
		return ipadCreator;
	}

	public void setIpadCreator(String ipadCreator) {
		this.ipadCreator = ipadCreator;
	}

	public String getIpadBrand() {
		return ipadBrand;
	}

	public void setIpadBrand(String ipadBrand) {
		this.ipadBrand = ipadBrand;
	}
}