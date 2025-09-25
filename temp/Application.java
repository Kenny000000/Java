import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	static ArrayList<Product> stock = new ArrayList<Product>();
	static ArrayList<Product> cart = new ArrayList<Product>();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Scanner input = new Scanner(System.in);
	static int centralID = 107;
	static int totalTakings = 0;
	static double totalSales = 0;
	
	public static void main(String[] args) {
		File saveFile = new File("data.ser");
		
		if(saveFile.exists()){
			try {
				loadData();
			} catch (Exception e) {
				System.out.println("Error Loading File");
			}
			mainMenu();
		}
		else {
			prePopulate();
			mainMenu();
		}
	}

	private static void mainMenu() {
		System.out.println("----------------------------------------------");
		System.out.println("Press 1 - for Product Menu");
		System.out.println("Press 2 - for Sales Menu");
		System.out.println("Press 3 - for Admin");
		System.out.println("Press x - to Save and Exit");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		switch(choice) {
			case "1":{
				productMenu();
				break;
			}
			case"2":{
				salesMenu();
				break;
			}
			case"3":{
				admin();
				break;
			}
			case "x","X":{
				try {
					saveData();
				} catch (Exception e) {
					System.out.println("Error Saving File.");
				}
				System.out.println("System is Saved. System is now Closing.");
				System.exit(0);//Closes the Program
			}
		}
		mainMenu();
		
	}

	private static void admin() {
	    System.out.println("Total Available Products: " + stock.size());
	    System.out.println("Total Transactions: €" + totalSales);
	    System.out.println("Total Number of Sales: " + totalTakings);
	    System.out.println("Average Sales per Transactions: €" + totalSales/totalTakings);
	    double stockValue = 0;
	    
	    for (Product p : stock) {
	        stockValue += (p.getProductPrice() * p.getProductQty());
	    }
	    System.out.println("Stock Value: €" + stockValue);
	}

	private static void salesMenu() {
		System.out.println("----------------------------------------------");
		System.out.println("1 - View Products");
		System.out.println("2 - Add to Cart");
		System.out.println("3 - Remove from Cart");
		System.out.println("4 - View Cart");
		System.out.println("5 - Checkout");
		System.out.println("m - Main Menu");
		String choice = input.next();
		switch(choice) {
			case"1":{
				viewAllProducts();
				break;
			}
			case"2":{
				addtoCart();
				break;
			}
			case"3":{
				removeFromCart();
				break;
			}
			case"4":{
				viewCart();
				break;
			}
			case"5":{
				checkout();
				break;
			}
			case"m","M":{
				mainMenu();	
				break;
			}
		}
	}

	private static void checkout() {
		if (cart.isEmpty()) {
            System.out.println("Cart is Empty");
        } else {
            double total = 0;
            int item = 1;
            System.out.println("--------------------- Checkout --------------------------------");
            for (Product c : cart) {
            	System.out.println("-------------------------Item " + item + "---------------------------------");
            	System.out.println("Product ID: " + c.getProductID() );
                System.out.println( "Product: " + c.getProductName());
                System.out.println("Quantity: " + c.getProductQty());
                System.out.println("Price per unit: €" + c.getProductPrice());
                System.out.println("Total: €" + c.getProductPrice() * c.getProductQty());
                total += c.getProductPrice() * c.getProductQty();
                item++;
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("Your Checkout Total is: €" + total);
            System.out.println("Would you like to pay with Debit Card or Credit Card");
            System.out.println("1 - Debit Card");
            System.out.println("2 - Credit Card");
            System.out.println("3 - To exit out of checkout");
            String choice = input.next();
            switch (choice) {
            	case "1":{
	            	System.out.println("You have succesfully paid with your debit card");
	            	System.out.println("Thank you for shopping come again!");
	            	totalTakings += item;
	            	totalSales += total;
	            	cart.clear();
	            	mainMenu();
	            	break;
            	}
            	case "2":{
            		System.out.println("You have succesfully paid with your Credit card");
                	System.out.println("Thank you for shopping come again!");
                	totalTakings += item;
	            	totalSales += total;
                	cart.clear();
                	mainMenu();
                	break;
            	}
            	case "3":{
            		salesMenu();
            		break;
            	}
            }
        }
	}

	private static void removeFromCart() {
		if (cart.isEmpty()) {
	        System.out.println("An error was found as cart is empty. Please try again after added an item in the cart");
	    } else {
	        viewCart(); // Print current items in the cart
	        System.out.println("Remove Product from cart by its ID");
	        int cartID = input.nextInt();
	        boolean isFound = false;
	        
	        for (Product cartItem : cart) {
	            if (cartItem.getProductID() == cartID) {
	                isFound = true;
	                for (Product stockItem : stock) {
	                    if (stockItem.getProductID() == cartID) {
	                        stockItem.setProductQty(stockItem.getProductQty() + cartItem.getProductQty());
	                        break;
	                    }
	                }
	                cart.remove(cartItem);
	                System.out.println("Product has succesfully been removed from cart.");
	                break;
	            }
	        }
	        
	        if (isFound==false) {
	            System.out.println("No Product with ID " + cartID + " found in the cart.");
	        }
	    }
	}
		
	private static void viewCart() {
		if(cart.isEmpty()) {
			System.out.println("Cart is Empty");

		}
		else {
			double total = 0;
			int item = 1;
			System.out.println("Here are your  cart lists:");
		for(Product c : cart) {
			System.out.println("-------------------------Item " + item + "---------------------------------");
			System.out.println("Product ID: " + c.getProductID() );
			System.out.println("Product: " + c.getProductName());
			System.out.println("Quantity: " + c.getProductQty());
			System.out.println("Price per Unit: €" + c.getProductPrice());
			System.out.println("Total price of " + c.getProductName() + " € " + c.getProductPrice() * c.getProductQty());
			total += c.getProductPrice() * c.getProductQty();
			item++;
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("Your Cart Total is: €" + total);
		}
	}

	private static void addtoCart() {
		viewAllProducts();//Print list of products
		System.out.println("Add to Product to cart by ID number");
		int cartID = input.nextInt();
		boolean isFound = false;
		for(Product stockItem: stock) {
			if(stockItem.getProductID()==cartID) {
				isFound = true;
				Product cartItem = new Product();//Create a new Product
				cartItem.setProductID(stockItem.getProductID());
				cartItem.setProductName(stockItem.getProductName());
				cartItem.setProductType(stockItem.getProductType());
				cartItem.setProductPrice(stockItem.getProductPrice());
				System.out.println("You have chosen " + cartItem.getProductName());
				System.out.println("Please enter amount to add to cart");
				int cartQty = input.nextInt();
				
				
				if(cartQty<=stockItem.getProductQty()) {
					cartItem.setProductQty(cartQty);
					int stockQty = stockItem.getProductQty() - cartQty;
					stockItem.setProductQty(stockQty);
					cart.add(cartItem);
					System.out.println(cartItem.getProductName() + " has successfully been added to cart.");
				}
				else{
					System.out.println("Insufficient Stock. Item has been cancelled.");
				}
			}
		}
		if(isFound==false){
			System.out.println("No Product with " + cartID + " id found.");
		}
	}

	private static void productMenu() {
		System.out.println("1 - Create Product");
		System.out.println("2 - Delete Product");
		System.out.println("3 - View Products");
		System.out.println("m - Back to Main Menu");
		String choice = input.next();
		
		switch(choice) {
			case"1":{
				try {
					createPhone();
				} catch (Exception e) {
					System.out.println("Error Creating Phone");
				}
				break;
			}
			case"2":{
				deleteProduct();
				break;
			}
			case"3":{
				viewBothProducts();
				break;
			}
			case"m","M":{
				mainMenu();
				break;
			}
		}
		
	}

	private static void viewBothProducts() {
		System.out.println("--------------------------------");
		System.out.println("Please Select a Category:");
		System.out.println("1 - View Only Phones");
		System.out.println("2 -  View Only Ipad");
		System.out.println("3 - View Both Products");
		System.out.println("m - Back to Main Menu");
		String choice = input.next();
		
		switch(choice) {
			case"1":{
				viewOnlyPhones();
				break;
			}
			case"2":{
				viewOnlyIpad();
				break;
			}
			case"3":{
				viewAllProducts();
				break;
			}
			case"m","M":{
				mainMenu();
				break;
			}
		}
		
	}
	
	private static void viewAllProducts() {
		for(Product p: stock) {
			p.printDetails();
		}
	}

	private static void viewOnlyIpad(){
		for(Product p: stock) {
			if(p.getProductType().equals("Ipad")){
				Ipad i = (Ipad)p;
				System.out.println("------------------------------------------------------------------------");
				System.out.println(p);
				System.out.print("\t" + "ID:" + i.getProductID());
				System.out.print("\t" + i.getProductName());
				System.out.print("\t" + "Type: " + i.getProductType());
				System.out.print("\t" + "Quantity: "+ i.getProductQty());
				System.out.print("\t" + " €" +i.getProductPrice() + "\n");
			}
		}
		mainMenu();
	}
		
	private static void viewOnlyPhones() {
		for(Product p: stock) {
			if(p.getProductType().equals("Phone")) {
				Phone ph = (Phone)p;
				System.out.println("------------------------------------------------------------------------");
				System.out.print("\t" + "ID:" + ph.getProductID());
				System.out.print("\t" + ph.getProductName());
				System.out.print("\t" + "Type: " +ph.getProductType());
				System.out.print("\t" + ph.getProductQty());
				System.out.print("\t" + "€" + ph.getProductPrice() + "\n");
			}
		}
		mainMenu();
	}

	private static void deleteProduct() {
		viewAllProducts();
		System.out.println("Choose Guest to Delete by ID number");
		int toDelete = input.nextInt();
		boolean isFound = false;
		
		for(Product p: stock) {
			if(p.getProductID()==toDelete) {
				isFound = true;
				System.out.println(p.getProductName() + " has been deleted");
				stock.remove(p);
				break;
			}
		}
		if(isFound==false) {
			System.out.println("No mathcing id number found.");
		}
	}

		private static void createPhone() throws Exception{
		Phone p = new Phone();
		p.setProductID(centralID);
		centralID++;
		System.out.println("Enter the type of Product");
		p.setProductType(reader.readLine());
		System.out.println("Enter the Founder of this Phone");
		p.setPhoneCreator(reader.readLine());
		System.out.println("Enter Phone Name");
		p.setProductName(reader.readLine());
		System.out.println("Enter Phone Brand");
		p.setPhoneBrand(reader.readLine());
		System.out.println("Enter Price");
		p.setProductPrice(input.nextDouble());
		System.out.println("Enter Quantity of Phone");
		p.setProductQty(input.nextInt());
		stock.add(p);
		System.out.println(p.getProductType() + " has been succesfully Created");
		saveData();//Auto Save Feature
		
		
	}
	private static void saveData() throws Exception{
		//Creates a file called data.ser
		FileOutputStream exportFile = new FileOutputStream("data.ser");
		
		//Opens the file. Creates the ability to write to the data.ser file
		ObjectOutputStream writer = new ObjectOutputStream(exportFile);
		
		//Save the ArrayList<Product> stock to data.ser
		writer.writeObject(stock);//Closed File (If file is not closed it will crash)
		
	}

	private static void loadData() throws Exception{
		FileInputStream importFile = new FileInputStream("data.ser");
		ObjectInputStream objReader = new ObjectInputStream(importFile);
		stock = (ArrayList<Product>)objReader.readObject();
		centralID = centralID + stock.size();//Update ID number
		objReader.close();
	}

	private static void prePopulate() {
		Phone apple = new Phone(101, "Iphone 15", 899.9, 200, "Phone", "Steve Jobs","Apple");
		Phone samsung = new Phone(102, "Samsung s23", 650.9,150,"Phone","Lee Byung-Chull", "Samsung" );
		Phone nokia = new Phone(103, "Nokia XR21", 449.99, 100, "Phone", "Fredrick Idestam", "Nokia");
		Ipad apple2 = new Ipad(104, "APPLE 10.9", 469.99, 50, "Ipad", "Steve Jobs","Apple");
		Ipad samsung2 = new Ipad(105,"Samsung Galaxy Tab S9",479.99,25,"Ipad", "Lee Byung-Chull", "Samsung");
		Ipad nokia2 = new Ipad(106,"Nokia T21", 229.99, 15, "Ipad", "Fredrick Idestam", "Nokia");
		stock.add(apple);
		stock.add(samsung);
		stock.add(nokia);
		stock.add(apple2);
		stock.add(samsung2);
		stock.add(nokia2);
	}
}
