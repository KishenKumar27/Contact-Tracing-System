# Contract Tracing System

## Project Description
This system tracks customer visits to various shops, including details such as visit times and dates during the Covid-19 period. It uses Java for implementation and CSV files for data storage. The application is designed to handle shop, customer, and check-in data, providing essential functionalities for managing and retrieving information.

## Features
1. **Manage Shops:**
   - Add, update, and retrieve shop details.
   - Track shop visits by customers.

2. **Manage Customers:**
   - Add, update, and retrieve customer details.
   - Track customer visits to shops.

3. **Visit Tracking:**
   - Record customer check-ins with timestamps.
   - Link visits to both customers and shops.

4. **File Handling:**
   - Read and write data from/to CSV files for persistence.
   - Files: `Shop.csv`, `Customers.csv`, `Checkin.csv`

## File Structure

### Root Directory
```
JavaDoc/
Source Codes/
Assignment1.pdf
Members.txt
```

### Source Codes Directory
```
bin/main/
doc/
src/main/
Checkin.csv
Customers.csv
Shop.csv
Task.iml
```

### `src/main` Directory
```
Admin.java
CheckIn.java
Customer.java
Shop.java
TestClass.java
Utils.java
Visit.java
```

## Classes and Responsibilities

### 1. `Admin.java`
Handles administrative functionalities.

### 2. `CheckIn.java`
Represents a check-in event with attributes for customer name, shop name, date, and time.

### 3. `Customer.java`
Represents a customer with attributes such as name, phone number, status, and visited shops.

### 4. `Shop.java`
Represents a shop with attributes such as name, phone number, manager, and status.

### 5. `TestClass.java`
Provides a test suite for verifying the system’s functionality.

### 6. `Utils.java`
Contains utility methods for file handling, including reading and writing CSV files and managing relationships between customers, shops, and visits.

### 7. `Visit.java`
Represents a visit with attributes for time and date.

## How to Run the Project
1. Clone the repository or download the source code.
2. Navigate to the `src/main` directory.
3. Compile and run `TestClass.java`.
4. Ensure that `Shop.csv`, `Customers.csv`, and `Checkin.csv` are present in the `Source Codes` directory for correct functionality.

## Task Distribution
See `Members.txt` for details about team member responsibilities.

## License
This project is part of the TCP1201 Objected-Oriented Programming and Data Structures coursework.
