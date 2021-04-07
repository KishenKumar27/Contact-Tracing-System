package main;

public class CheckIn {

	private String customer;
	private String shop;
	private String date;
	private String time;

	public CheckIn(String customer, String shop, String date, String time) {
		this.customer = customer;
		this.shop = shop;
		this.date = date;
		this.time = time;
	}

	public String getCustomer() {
		return customer;
	}

	public String getShop() {
		return shop;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
