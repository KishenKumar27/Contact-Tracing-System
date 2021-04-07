package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestClass {

	private static ArrayList<Customer> customerlist = new ArrayList<Customer>();
	private static ArrayList<Shop> shoplist = new ArrayList<Shop>();
	private static Scanner input = new Scanner(System.in);
	private static Customer currentCustomer;
	static Utils utils = new Utils();

	/*
	Main method that defines ArrayList for reading the Checkin Csv File, Create the object reference
	variables for reading the Shop.csv and accessing the ArrayList and call the Main Menu Function.
	*/
	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<CheckIn> readCheckInFile = Utils.ReadCheckInFile();
		customerlist = Utils.getCustomers(readCheckInFile);

		shoplist = Utils.readShopFile();

		userType();
	}

	/*
	Provides Customer Menu After We Choose The 'Customer' Role In The Main Menu And Call The Register Method
	In The Customer Class To Make The User Register As A New Customer Or Call The Login Method In the Customer
	Class To Make The User Login As An Old Customer.
	*/
	public static void handleAuthentication() {
		try {
			System.out.println("\n-----------------");
	        System.out.println("| Customer Menu |");
	        System.out.println("-----------------\n");
			System.out.println("[1] Register");
			System.out.println("[2] Login");
			System.out.println("[3] Go Back");
			System.out.print("\nInput (1..3): ");
			int choice = input.nextInt();

			if (choice == 1) {
				System.out.println("\n----------------");
		        System.out.println("| Registration |");
		        System.out.println("----------------\n");
				Customer.register(customerlist);
				Utils.WriteCustomerFile(customerlist);
			}

			else if (choice == 2) {
				System.out.println("\n-------");
		        System.out.println("|Login|");
		        System.out.println("-------\n");
				currentCustomer = Customer.signin(customerlist);
				if (currentCustomer != null) {
					customerMenu();
				}

				else {
					System.out.println("\nWrong Customer Name or Phone Number...");
					handleAuthentication();
				}
			}
			
			else if (choice == 3)
			   userType();
			
			else
				System.out.println("\nInvalid input...");
			    handleAuthentication();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

   /*
   Provides check-in a shop access, view the history of the shops, view the status after the user login as
   a customer.
   */
	public static void customerMenu() {
		System.out.println("\n------------------");
        System.out.println("|Welcome Customer|");
        System.out.println("------------------");
		System.out.println("\n[1] Check-in a shop");
		System.out.println("[2] View the history of the shops");
		System.out.println("[3] View his/her status");
		System.out.println("[4] Go Back");
		 System.out.print("\nInput (1..4): ");

		int choice = input.nextInt();
		System.out.println();
		
		if (choice == 1) {
			System.out.println("\nCheck-In");
			System.out.println("--------\n");
			LocalTime time = LocalTime.now();
			LocalDate day = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String value = formatter.format(time);
			Shop selectShop = selectShop();
			Customer.checkIn(selectShop, currentCustomer, value, day.toString());
			Utils.WriteCheckInFile(customerlist);
			System.out.println("\nCustomer has been successfully checked in !!!");

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				customerMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 2) {
			System.out.println("\nHistory");
			System.out.println("-------\n");
			Customer.viewhistory(currentCustomer);

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				customerMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 3) {
			System.out.println("\nStatus");
			System.out.println("------\n");
			Customer.viewStatus(currentCustomer);

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				customerMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 4)
			handleAuthentication();
		
		else
			System.out.println("\nInvalid input...");
		    customerMenu();
			
	}

	/*
	  After the user chooses check-in a shop option as a customer, this method helps to determine the name of the shop
	  that the user wanted to check-in a shop.
	*/
	public static Shop selectShop() {
		Shop selectedShop ;
		int i = 1;
		for (Shop shop : shoplist) {
			System.out.println("[" + i + "] " + shop.getName());
			i++;
		}

		while (true) {
			System.out.println("\nEnter Shop Name(Tesco...7Eleven)");
			String choice = input.next();

			for (Shop shop : shoplist) {
				if (shop.getName().equals(choice)) {
					selectedShop = shop;
					return selectedShop;
				}
			}
			System.out.println("\nInvalid Name, Please Try Again...");
		}
	}

	/*
	Main menu method that prompts the user to choose a role from the 2 roles, which are Admin, and Customer.
	*/
	public static void userType() {
		title();
		System.out.println("---------------");
	    System.out.println("|  Main Menu  |");
	    System.out.println("---------------\n");
		System.out.println("[1] Admin");
		System.out.println("[2] Customer");
		System.out.println("[3] Exit");
		System.out.print("\nEnter the role(1...3): ");
		
		int choice = input.nextInt();
		if (choice == 1) {
			AdminLogin();
		}

		else if (choice == 2)
			handleAuthentication();

		else if (choice == 3)
			ending();
		
		else
			System.out.println("\nInvalid input...");
		    userType();
		

	}

	/*
	  Provides Menu for the admin after the user chooses and login as the 'Admin' Role and it also provides
	  the key to the admin that he/she has the access to view shop details, view customer details, view Master
	  Visit History and flag the customers if they wanted to.
	*/
	public static void AdminMenu() {
		System.out.println("\n------------");
        System.out.println("|ADMIN MENU|");
        System.out.println("------------\n");
        
		System.out.println("[1] View Shop Details");
		System.out.println("[2] View Customer Details");
		System.out.println("[3] View Master Visit History");
		System.out.println("[4] Flag Customer");
		System.out.println("[5] Go Back");
		
		System.out.print("\nInput(1..5): ");

		int choice = input.nextInt();
		if (choice == 1) {
			System.out.println("\nShop Details");
			System.out.println("------------\n");
			Admin.shopDetail(shoplist);

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				AdminMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 2) {
			System.out.println("\nCustomer Details");
			System.out.println("----------------\n");
			Admin.customersDetail(customerlist);

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				AdminMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 3) {
			System.out.println("\nMaster Visit History");
			System.out.println("--------------------\n");
			generateRandomCheckin();

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				AdminMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}

		else if (choice == 4) {
			System.out.println("\nFlag Customer");
			System.out.println("-------------\n");
			System.out.println("Enter Customer Name");
			String name = input.next();
			System.out.println("Enter Time in format (21:55:23.955)");
			String Time = input.next();
			LocalTime time = LocalTime.parse(Time);
			System.out.println("Enter Date in format (2021-01-07)");
			String date = input.next();

			Admin.Flag(name, customerlist, time, date);

			Utils.WriteCustomerFile(customerlist);
			
			System.out.println("\nThe customer has been successfully flagged...");

			System.out.print("\nDo you want to go back?(Press 'Y' for Yes or 'N' for No): ");
			String ch = input.next();

			if (ch.toLowerCase().equals("y"))
				AdminMenu();

			else if (ch.toLowerCase().equals("n")) {
				System.exit(0);
			}
		}
		
		else if (choice == 5)
			userType();
		
		else
			System.out.println("\nInvalid input...");
		    AdminMenu();
			
	}

	/*
	  Provides random visits of the customers to the shops in the master visit history.
	*/
	public static void generateRandomCheckin() {
		Random n = new Random();
		int pran = 0;
		int pran1 = 0;

		for (int i = 0; i < 30; i++) {

			int ran = n.nextInt(4);
			int ran1 = n.nextInt(5);
			int ranhour = n.nextInt(24);
			int ranminute = n.nextInt(59);
			int ranSec = n.nextInt(59);


			LocalTime t = LocalTime.now();
			LocalDate today = LocalDate.now();

			LocalTime plustime = t.plusHours(ranhour).plusMinutes(ranminute).plusSeconds(ranSec);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String value = plustime.format(formatter);

			if (pran != ran && pran1 != ran1) {
				Customer checkIn = Customer.checkIn(shoplist.get(ran), customerlist.get(ran1), value, today.toString());

			}
			else {
				ran = n.nextInt(4);
				ran1 = n.nextInt(5);
			}

			pran = ran;
			pran1 = ran1;

		}
		Admin.masterVisitHistory(customerlist);
	}

	/*
	  Provides security to the admin by asking the user to enter username and password before he/she access
	  the admin system and also symbolizes that everyone don't have the access to the admin system.
	*/
	public static void AdminLogin() {
        boolean running = true;
        System.out.println("\n-------------------");
        System.out.println("|LOGIN AS AN ADMIN|");
        System.out.println("-------------------");
        
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter username: ");
            String userName = scanner.nextLine();

            System.out.print("\nEnter password: ");
            String password = scanner.nextLine();

            if ("admin".equals(userName.toLowerCase()) && "123admin".equals(password.toLowerCase())) {
                running = false;
                System.out.println("\nAdmin successfully logged-in...\n");
                AdminMenu();

            } else {
                System.out.println("\nInvalid username or password ");
            }
        }while (running);

    }

    /*
      Method that displays the title of the system.
    */
	public static void title() {
        System.out.println("\n********************");
        System.out.println("||COVID-19 Contact||");
        System.out.println("||Tracing System  ||");
        System.out.println("********************\n");

    }

	/*
      Method that displays this output after the user exits the system.
    */
    public static void ending() {
        System.out.println();
        System.out.println("\n************************");
        System.out.println("||      THE END       ||");
        System.out.println("||THANK YOU FOR USING ||");
        System.out.println("||    THIS SYSTEM     ||");
        System.out.println("************************");
        System.exit(0);
    }

}
