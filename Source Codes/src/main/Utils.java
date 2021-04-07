package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

	public static ArrayList<Shop> readShopFile() {
		ArrayList<Shop> shoplist = new ArrayList<Shop>();
		String row;
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Shop.csv"));
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");
				Shop shop = new Shop(data[0], data[1], data[3], data[2]);
				shoplist.add(shop);
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return shoplist;
	}

	public static void WriteShopFile(ArrayList<Shop> shoplist) {
		try {
			FileWriter csvWriter = new FileWriter("Shop.csv");

			for (Shop shop : shoplist) {
				csvWriter.append(shop.getName());
				csvWriter.append(",");
				csvWriter.append(shop.getPhone());
				csvWriter.append(",");
				csvWriter.append(shop.getManager());
				csvWriter.append(",");
				csvWriter.append(shop.getStatus());
				csvWriter.append("\n");
			}

			csvWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<Customer> ReadCustomerFile() {
		ArrayList<Customer> customerlist = new ArrayList<Customer>();
		String row;
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Customers.csv"));
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");
				Customer customer = new Customer(data[0], data[1], data[2]);
				customerlist.add(customer);
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customerlist;
	}

	public static void WriteCustomerFile(ArrayList<Customer> customerlist) {
		try {
			FileWriter csvWriter = new FileWriter("Customers.csv");
			for (Customer customer : customerlist) {
				csvWriter.append(customer.getName());
				csvWriter.append(",");
				csvWriter.append(customer.getPhone());
				csvWriter.append(",");
				csvWriter.append(customer.getStatus());
				csvWriter.append("\n");
			}

			csvWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<CheckIn> ReadCheckInFile() throws FileNotFoundException {

		ArrayList<CheckIn> list = new ArrayList<CheckIn>();
		Scanner input = new Scanner(new File("Checkin.csv"));
		while (input.hasNext()) {

			String row = input.nextLine();
			String[] data = row.split(",");

			CheckIn checkIn = new CheckIn(data[0], data[1], data[2], data[3]);
			list.add(checkIn);
		}

		input.close();

		return list;

	}

	public static Customer getCustomer(String name) {
		ArrayList<Customer> readCustomerFile = ReadCustomerFile();
		Customer customer1 = new Customer();
		for (Customer customer : readCustomerFile) {
			if (customer.getName().equals(name)) {
				customer1 = customer;
			}
		}
		return customer1;
	}

	public static Shop getShop(String name, Visit visit) {
		ArrayList<Shop> readShopFile = readShopFile();
		Shop Shop = new Shop();
		for (Shop shop : readShopFile) {
			if (shop.getName().equals(name)) {
				shop.setVisit(visit);
				Shop = shop;
			}
		}
		return Shop;
	}

	public static ArrayList<Customer> getCustomers(ArrayList<CheckIn> clist) {

		ArrayList<Customer> list = new ArrayList<Customer>();

		for (CheckIn checkIn : clist) {
			Customer customer = getCustomer(checkIn.getCustomer());
			Visit visit = new Visit(checkIn.getTime(), checkIn.getDate());
			Shop shop = getShop(checkIn.getShop(), visit);
			ArrayList<Shop> visitedShop = customer.getVisitedShop();
			visitedShop.add(shop);
			customer.setVisitedShop(visitedShop);
			list.add(customer);
		}

		for (int i = 0; i < list.size(); i++) {
			Customer customer1 = list.get(i);
			for (int j = 0; j < list.size(); j++) {
				Customer customer2 = list.get(j);
				if (i != j) {
					if (customer1.getName().equals(customer2.getName())) {
						
						Shop Shop = customer1.getVisitedShop().get(0);
						customer2.getVisitedShop().add(Shop);
						list.remove(customer1);
					}
				}
			}
		}

		ArrayList<Customer> readCustomerFile = ReadCustomerFile();
		for (Customer customer : readCustomerFile) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.contains(customer)) {
					list.add(customer);
				}
			}
		}
		

		return list;
	}

	public static void WriteCheckInFile(ArrayList<Customer> customerlist) {
		try {
			FileWriter csvWriter = new FileWriter("Checkin.csv");
			for (Customer customer : customerlist) {
				ArrayList<Shop> visitedShop = customer.getVisitedShop();
				for (Shop shop : visitedShop) {
					csvWriter.append(customer.getName());
					csvWriter.append(",");
					csvWriter.append(shop.getName());
					csvWriter.append(",");
					csvWriter.append(shop.getVisit().getDate());
					csvWriter.append(",");
					csvWriter.append(shop.getVisit().getTime());
					csvWriter.append("\n");
				}
			}

			csvWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
