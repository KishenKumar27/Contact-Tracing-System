package main;

import java.time.LocalTime;
import java.util.ArrayList;

public class Admin {

	public static void customersDetail(ArrayList<Customer> customerlist) {
		System.out.println(String.format("%s %7s %14s %13s", "No", "Name", "Phone", "Status"));
		for (int i = 0; i < customerlist.size(); i++) {
			System.out.println(String.format("%s %10s %15s %10s", (i + 1), customerlist.get(i).getName(),
					customerlist.get(i).getPhone(), customerlist.get(i).getStatus()));
		}
	}

	public static void masterVisitHistory(ArrayList<Customer> customerlist) {
		System.out.println(String.format("%s %10s %12s %13s %9s", "No", "Date", "Time", "Customer", "Shop"));
		for (int i = 0; i < customerlist.size(); i++) {
			Customer customer = customerlist.get(i);
			ArrayList<Shop> visitedshop = customer.getVisitedShop();
			for (int j = 0; j < visitedshop.size(); j++) {
				System.out.println(String.format("%s %15s %10s %10s %10s", (i + 1),
						visitedshop.get(j).getVisit().getDate(), visitedshop.get(j).getVisit().getTime(),
						customer.getName(), visitedshop.get(j).getName()));
			}

		}

	}

	public static void shopDetail(ArrayList<Shop> shoplist) {
		System.out.println(String.format("%s %8s %12s %15s %9s", "No", "Name", "Phone", "Manager", "Status"));
		for (int i = 0; i < shoplist.size(); i++) {
			System.out.println(String.format("%s %10s %15s %10s %10s", String.valueOf(i + 1), shoplist.get(i).getName(),
					shoplist.get(i).getPhone(), shoplist.get(i).getManager(), shoplist.get(i).getStatus()));
		}
	}

	public static void Flag(String name, ArrayList<Customer> randomCustomer, LocalTime time, String date) {

		ArrayList<Shop> customerVisitedshop = new ArrayList<Shop>();
		ArrayList<Customer> allCustomerVisitedshop = new ArrayList<Customer>();

		// extract customer visited shops by customer name
		for (Customer customer : randomCustomer) {
			if (customer.getName().equals(name)) {
				customer.setStatus("case");
				customerVisitedshop = customer.getVisitedShop();
				randomCustomer.set(randomCustomer.indexOf(customer), customer);
			}
		}

		ArrayList<Shop> readShopFile = Utils.readShopFile();



		for (Shop shop : customerVisitedshop) {
			shop.setStatus("Case");
			customerVisitedshop.set(customerVisitedshop.indexOf(shop), shop);

			for (Shop shop2 : readShopFile) {
				if (shop2.getName().equals(shop.getName())) {

					shop2.setStatus("Case");
					readShopFile.set(readShopFile.indexOf(shop2), shop2);
				}
			}

		}

		Utils.WriteShopFile(readShopFile);

		// all customer that visited or check-in the shops visited by customer with covid cases
		for (Shop shop : customerVisitedshop) {
			for (Customer customer : randomCustomer) {
				ArrayList<Shop> visitedShop1 = customer.getVisitedShop();
				for (Shop shop2 : visitedShop1) {
					if (shop.getName().equals(shop2.getName())) {
						allCustomerVisitedshop.add(customer);
					}
				}
			}
		}


		// calculate the close contact customer from shop visited by covid case
		for (Customer customer : allCustomerVisitedshop) {
			ArrayList<Shop> visitedShop = customer.getVisitedShop();
			for (Shop shop : visitedShop) {
				LocalTime now = LocalTime.parse(shop.getVisit().time);
				LocalTime max = now.plusHours(1);
				LocalTime min = now.minusHours(1);
				// check the time is between the max and min limit which is exactly one hour
				if (time.isAfter(min) && time.isBefore(max) && date.equals(shop.getVisit().date)) {
					if (!customer.getStatus().equals("case")) {
						customer.setStatus("close");
						allCustomerVisitedshop.set(allCustomerVisitedshop.indexOf(customer), customer);
					}
				}
			}
		}

		for (Customer customer : randomCustomer) {
			System.out.println(customer.toString());
		}

	}

}

