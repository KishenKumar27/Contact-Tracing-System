package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Customer class represents values associated to the customer .
 */

public class Customer {

	private static Scanner input = new Scanner(System.in);
	private String name;
	private String phone;
	private String status;
	private ArrayList<Shop> visitedShop = new ArrayList<Shop>();

	/**
	 * Constructs a customer with values such as name, phone and status
	 *
	 * @param name the name value of customer
	 * @param phone the phone value of customer
	 * @param status the status value of customer
	 */
	public Customer(String name, String phone, String status) {
		this.name = name;
		this.phone = phone;
		this.status = status;
	}

	/**
	 * Constructs a customer with values set to zero.
	 */
	public Customer() {

	}

	/**
	 * Registers the customer information into the list
	 *
	 * @param list array list to store registered customer information
	 */
	public static void register(ArrayList<Customer> list) {
		Customer customer = new Customer();

		System.out.println("Enter Customer Name");
		String name = input.next();

		System.out.println("\nEnter Customer Phone Number");
		String phone = input.next();

		customer.setName(name);
		customer.setPhone(phone);
		customer.setStatus("Normal");

		list.add(customer);
		System.out.println("\nCustomer Registered !!!");

	}

	/**
	 * Signs in the customer by validating its values with the registered value
	 *
	 * @param customerlist  array list to store customer sign in information
	 * @return null
	 * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname has failed
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 */

	public static Customer signin(ArrayList<Customer> customerlist) throws FileNotFoundException, IOException {
		Customer customer = null;
		BufferedReader csvReader = new BufferedReader(new FileReader("Customers.csv"));
		System.out.println("Enter Customer Name");
		String name = input.next();
		System.out.println("\nEnter Customer Phone Number");
		String phone = input.next();
		for (Customer customer2 : customerlist) {
			if (customer2.getName().equals(name) && customer2.getPhone().equals(phone)) {
				customer = customer2;
				return customer;
			}
		}
		csvReader.close();
		return null;
	}

	/**
	 *  Returns the details of customer check-ins
	 *
	 * @param shop  the shop value that was checked-in
	 * @param customer the customer value that was checked-in
	 * @param time the time value that was checked-in
	 * @param date the date value that was checked-in
	 * @return customer value
	 */
	public static Customer checkIn(Shop shop, Customer customer, String time, String date) {
		ArrayList<Shop> visitedshop = customer.getVisitedShop();
		Visit visit = new Visit(time, date);
		shop.setVisit(visit);
		visitedshop.add(shop);
		customer.setVisitedShop(visitedshop);
		return customer;
	}

	/**
	 *  View the status of a customer
	 *
	 * @param customer the customer value of Customer
	 */
	static void viewStatus(Customer customer) {
		System.out.println("Status: " + customer.getStatus());
	}

	/**
	 * Getter for the value name
	 *
	 * @return name value
	 */

	public String getName() {
		return name;
	}

	/**
	 * Getter for the value phone
	 *
	 * @return phone value
	 */

	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for the value status
	 *
	 * @return status value
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * Setter for the value name
	 *
	 * @param name the name value of customer
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for the value phone
	 *
	 * @param phone the phone value of customer
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Setter for the value status
	 *
	 * @param status the status value of customer
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for the value visitedShop
	 *
	 * @return visitedShop value
	 */

	public ArrayList<Shop> getVisitedShop() {
		return visitedShop;
	}

	/**
	 * Setter for the value visitedShop
	 *
	 * @param visitedShop the visitedShop value of customer
	 */

	public void setVisitedShop(ArrayList<Shop> visitedShop) {
		this.visitedShop = visitedShop;
	}

	/**
	 * View the history of shops visited by the customer
	 *
	 * @param customer the customer value of customer
	 */

	public static void viewhistory(Customer customer) {
		ArrayList<Shop> visitedshop = customer.getVisitedShop();
		System.out.println("No\t Date\t \tTime\t Shop\t");
		for (int i = 0; i < visitedshop.size(); i++) {
			System.out.println((i + 1) + "  " + visitedshop.get(i).getVisit().getDate() + "  "
					+ visitedshop.get(i).getVisit().getTime() + "  " + visitedshop.get(i).getName());
		}
	}

	/**
	 * Returns the customer values in a array like format
	 *
	 * @return name,phone,status,visitedShop
	 */

	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone=" + phone + ", status=" + status + ", visitedShop=" + visitedShop
				+ "]";
	}

	/**
	 * Returns a hash code value for the object
	 *
	 * @return result
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * The equals method implements an equivalence relation on non-null object references
	 *
	 * @param obj The object to compare this String against
	 * @return boolean true value if everything else fails
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}