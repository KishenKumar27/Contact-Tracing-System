package main;

public class Shop {

	private String name;
	private String phone;
	private String status;
	private String manager;
	private Visit visit = new Visit();

	public Shop() {

	}

	public Shop(String name, String phone, String status, String manager) {
		this.name = name;
		this.phone = phone;
		this.manager = manager;
		this.setStatus(status);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatus(String status) {
		if (status.equals("Normal") || status.contains("Case")) {
			this.status = status;
		} else {
			System.out.println("Invalid case");
		}
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getStatus() {
		return this.status;
	}

	public String getManager() {
		return this.manager;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public static String viewStatus(Shop shop) {
		return "Status : " + shop.getStatus();
	}

	@Override
	public String toString() {
		return "Shop [name=" + name + ", phone=" + phone + ", status=" + status + ", manager=" + manager + ", visit="
				+ visit + "]";
	}

}
