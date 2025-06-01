package org.example;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection; // Used to connect to the MySQL database
    private Scanner scanner;       // Used to take input from the user

    // Constructor to initialize connection and scanner
    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    /**
     * This method adds a new patient record to the database.
     * It collects patient details from user input and inserts them into the 'patients' table.
     */
    public void addPatient(){
        System.out.print("Enter Patient_id: ");
        int p_id = scanner.nextInt();

        System.out.print("Enter Patient Name: ");
        String name = scanner.next();

        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();

        try {
            // SQL query to insert a new patient record
            String query = "INSERT INTO patients(p_id, name, age, gender) VALUES(?, ?, ?, ?)";

            // Use PreparedStatement to prevent SQL injection and safely insert values
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, p_id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);

            // Execute the query and check if insertion was successful
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient Added Successfully!!");
            } else {
                System.out.println("Failed to add Patient!!");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print error details if insertion fails
        }
    }

    /**
     * This method retrieves and displays all patients from the 'patients' table.
     */
    public void viewPatients(){
        String query = "SELECT * FROM patients";
        try {
            // Prepare and execute the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Print header for patient data
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");

            // Loop through each patient record and print in a formatted way
            while (resultSet.next()) {
                int id = resultSet.getInt("p_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print error if query fails
        }
    }

    /**
     * This method checks whether a patient with a given ID exists in the database.
     * 
     * @param p_id The ID of the patient to search for.
     * @return true if the patient exists, false otherwise.
     */
    public boolean getPatientById(int p_id){
        String query = "SELECT * FROM patients WHERE p_id = ?";
        try {
            // Prepare the SQL query with patient ID
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, p_id);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Return true if patient exists
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace(); // Print error if any issue occurs
        }

        // Return false if exception occurs or patient not found
        return false;
    }
}
