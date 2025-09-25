import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * Represents a product in the inventory system.
 * Each product has a unique ID, name, price, quantity, and type.
 */

public class Product implements Serializable{

	private int productID;
	private String productName;
	private double productPrice;
	private int productQty;
	private String productType;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor for the Product Class
	 */
	public Product(){
		
	}
	/**
	 * Construct for the Product Class.
	 * 
	 * @param productID 	The unique identifier for the product.
	 * @param productName	The name of the product.
	 * @param productPrice	The price of the product.
	 * @param productQty	The quantity of the product.
	 * @param productType	The type of the product.
	 */
	public Product(int productID, String productName, double productPrice, int productQty, String productType) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.productType = productType;
	}
	
	/**
	 * Prints details of the product, with ID, name, price, type, and quantity.
	 */
	public void printDetails() {
		DecimalFormat currency = new DecimalFormat("€0.00");
		System.out.print("ID:"+ productID + "\t");
		System.out.print(productName + "\t");
		System.out.print(currency.format(productPrice) + "\t");
		System.out.print("Type: " + productType + "\t");
		System.out.println("Quantity: " + productQty);
		System.out.println("-------------------------------------------------------------------------");
	}
	/**
	 * Returns the product ID.
	 * 
	 * @return	The product ID.
	 */
	public int getProductID() {
		return productID;
	}
	/**
	 * Sets the product ID.
	 * 
	 * @param productID	The product ID to set.
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
	/**
	 * Returns the product name.
	 * 
	 * @return	The product name.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * Sets the product name.
	 * 
	 * @param productName	productName The product name to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * Returns the product price.
	 * 
	 * @return	The product price.
	 */
	public double getProductPrice() {
		return productPrice;
	}
	/**
	 * Sets the product price.
	 * 
	 * @param productPrice	The product price to set.
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	/**
	 * Returns the product quantity.
	 * 
	 * @return	The product quantity.
	 */
	public int getProductQty() {
		return productQty;
	}
	/**
	 * Sets the product quantity.
	 * 
	 * @param productQty	The product quantity to set.	
	 */
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	/**
	 * Returns the product type.
	 * 
	 * @return	The product Type.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * Sets the product type.
	 * 
	 * @param productType The product type to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
}