# Hospital Management System

A simple console-based Hospital Management System implemented in Java using JDBC and MySQL.  
This application allows managing patients, doctors, and booking appointments through a command-line interface.

---

## Features

- Add new patients  
- View all patients  
- View all doctors  
- Book appointments between patients and doctors  
- Basic input validation (checks for existing patients/doctors before booking)  

---

## Technologies Used

- Java (JDK 8+)  
- JDBC for database connectivity  
- MySQL as the database  
- Console-based user interface  

---

## Prerequisites

- Java JDK installed  
- MySQL server installed and running  
- MySQL database named `hospital_management_system` with the following tables:
  - `patients (p_id INT PRIMARY KEY, name VARCHAR, age INT, gender VARCHAR)`  
  - `doctors (d_id INT PRIMARY KEY, name VARCHAR, specialization VARCHAR)`  
  - `appointments (appointment_id INT PRIMARY KEY, p_id INT, d_id INT, appointment_date DATE)`  

---

## Setup Instructions

1. Clone or download this repository.  
2. Create and configure the MySQL database and tables as per the schema above.  
3. Update the database connection credentials in `Main.java` if necessary:  

```java
private static final String url = "jdbc:mysql://localhost:3306/hospital_management_system";
private static final String username = "root";
private static final String password = "your_password";

